package com.example.trailproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FruitsWholesaler extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView applequantity,orangequantity;
    int countApple=0;
    int countOrange = 0;
    Button WholesalerAppleSelectBtn,WholesalerOrangeSelectBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_wholesaler);
        applequantity = (TextView) findViewById(R.id.WholesalerAppleQuantityValue);
        orangequantity = (TextView) findViewById(R.id.WholesalerOrangeQuantityValue);
        WholesalerAppleSelectBtn=(Button)findViewById(R.id.WholesalerAppleQauntitySelectButton);
        WholesalerOrangeSelectBtn=(Button)findViewById(R.id.WholesalerOrangeQauntitySelectButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        WholesalerAppleSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfApple=applequantity.getText().toString();
                                int CostOfApple=35;
                               docRef.update("appleCost",String.valueOf(CostOfApple));
                                docRef.update("appleQuantity",String.valueOf(QuantityOfApple));


                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(FruitsWholesaler.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });


            }
        });
        WholesalerOrangeSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfOrange=orangequantity.getText().toString();
                                int CostOfOrange=35;
                                docRef.update("orangeCost",String.valueOf(CostOfOrange));
                                docRef.update("orangeQuantity",String.valueOf(QuantityOfOrange));


                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(FruitsWholesaler.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });


            }
        });

    }
    public void incrementapple(View v)
    {
        countApple++;
        applequantity.setText("" + countApple);
    }
    public void decrementapple(View v)
    {
        if(countApple<=0)
            countApple = 0;
        else
            countApple--;
        applequantity.setText("" + countApple);
    }
    public void incrementorange(View v)
    {
        countOrange++;
        orangequantity.setText("" + countOrange);
    }
    public void decrementorange(View v)
    {
        if(countOrange<=0)
            countOrange = 0;
        else
            countOrange--;
        orangequantity.setText("" + countOrange);
    }
}