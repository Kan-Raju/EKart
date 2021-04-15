package com.example.trailproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText phoneNumber,codeEnter;
    Button nextBtn;
    ProgressBar progressBar;
    TextView state;
    CountryCodePicker codePicker;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verificationInProgress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            int x = 2;
            int y=7;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            phoneNumber = findViewById(R.id.phone);
            codeEnter = findViewById(R.id.codeEnter);
            progressBar = findViewById(R.id.progressBar);
            nextBtn = findViewById(R.id.nextBtn);
            state = findViewById(R.id.state);
            codePicker = findViewById(R.id.ccp);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!verificationInProgress) {
                        if (!phoneNumber.getText().toString().isEmpty() && phoneNumber.getText().toString().length() == 10) {
                            String phoneNum = "+" + codePicker.getSelectedCountryCode() + phoneNumber.getText().toString();
                            Log.d(TAG, "onClick: Phone Number->" + phoneNum);
                            progressBar.setVisibility(View.VISIBLE);
                            state.setText("Sending OTP...");
                            state.setVisibility(View.VISIBLE);
                            requestOTP(phoneNum);

                        } else {
                            phoneNumber.setError("Phone number is not valid");
                        }
                    } else {
                        String userOTP = codeEnter.getText().toString();
                        if (!userOTP.isEmpty() && userOTP.length() == 6) {
                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, userOTP);
                            verifyAuth(credential);
                        } else {
                            codeEnter.setError("Valid OTP is required");
                        }
                    }
                }
            });
        }
        catch(Exception e){
            Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(fAuth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            state.setText("Checking..");
            state.setVisibility(View.VISIBLE);
            checkUserProfile();
        }
    }



    private void verifyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    checkUserProfile();
                }
                else {
                    Toast.makeText(Register.this,"Authentication failed ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUserProfile() {
        DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    if(documentSnapshot.getString("isCustomer")!=null){
                        startActivity(new Intent(getApplicationContext(),Customer.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isRetailer")!=null){
                        startActivity(new Intent(getApplicationContext(), Retailer.class));
                        finish();
                    }
                    if(documentSnapshot.getString("isWholesaler")!=null){
                        startActivity(new Intent(getApplicationContext(),Wholesaler.class));
                        finish();
                    }

                }
                else{
                    startActivity(new Intent(getApplicationContext(),AddDetails.class));
                    finish();
                }
            }
        });
    }

    private void requestOTP(String phoneNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeEnter.setVisibility(View.VISIBLE);
                verificationId=s;
                token=forceResendingToken;
                nextBtn.setText("Verify");
                /*nextBtn.setEnabled(false);*/
                verificationInProgress=true;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Toast.makeText(Register.this,"OTP is expired",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                verifyAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Register.this,"Cannot create account"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
