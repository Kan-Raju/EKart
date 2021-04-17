package com.example.trailproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    EditText firstName,lastName,email;
    Button saveBtn;
    String userID;

    CheckBox isCustomerBox,isRetailerBox,isWholesalerBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.emailAddress);
        saveBtn=findViewById(R.id.saveBtn);


        firebaseAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userID=firebaseAuth.getCurrentUser().getUid();

        Toast.makeText(this, "userId + " + userID, Toast.LENGTH_LONG).show();
        DocumentReference docRef= fStore.collection("users").document(userID);

        isCustomerBox=findViewById(R.id.isCustomer);
        isRetailerBox=findViewById(R.id.isRetailer);
        isWholesalerBox=findViewById(R.id.isWholesaler);


        isCustomerBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isRetailerBox.setChecked(false);
                    isWholesalerBox.setChecked(false);
                }

            }
        });
        isRetailerBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isCustomerBox.setChecked(false);
                    isWholesalerBox.setChecked(false);
                }
            }
        });
        isWholesalerBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    isCustomerBox.setChecked(false);
                    isRetailerBox.setChecked(false);
                }
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                    String first=firstName.getText().toString();
                    String last=lastName.getText().toString();
                    String userEmail=email.getText().toString();

                    Map<String,Object> user=new HashMap<>();
                    user.put("firstName",first);
                    user.put("lastName",last);
                    user.put("emailAddress",userEmail);

                    //access level
                    if(isCustomerBox.isChecked()){
                        user.put("isCustomer","1");
                    }
                    if(isRetailerBox.isChecked()){
                        user.put("isRetailer","2");
                    }
                    if(isWholesalerBox.isChecked()){
                        user.put("isWholesaler","3");
                    }

                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                try
                                {
                                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(SignUp.this,"error "+e.toString(),Toast.LENGTH_SHORT).show();
                                }
                                // finish();
                            }
                            else{
                                Toast.makeText(SignUp.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
                else{
                    Toast.makeText(SignUp.this,"All fields are mandatory.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}