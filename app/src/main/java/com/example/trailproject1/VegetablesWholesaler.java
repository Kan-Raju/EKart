package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VegetablesWholesaler extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView Tomatoquantity,Onionquantity;
    int count=0;
    Button WholesalerTomatoSelectBtn,WholesalerOnionSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_wholesaler);

        Tomatoquantity = (TextView) findViewById(R.id.WholesalerTomatoQuantityValue);
        Onionquantity = (TextView) findViewById(R.id.WholesalerOnionQuantityValue);

        WholesalerTomatoSelectBtn=(Button)findViewById(R.id.WholesalerTomatoQauntitySelectButton);
        WholesalerOnionSelectBtn=(Button)findViewById(R.id.WholesalerOnionQauntitySelectButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        WholesalerTomatoSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfTomato=Tomatoquantity.getText().toString();
                                int CostOfTomato=35;
                                docRef.update("tomatoCost",String.valueOf(CostOfTomato));
                                docRef.update("tomatoQuantity",String.valueOf(QuantityOfTomato));


                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(VegetablesWholesaler.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });
            }
        });
        WholesalerOnionSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfOnion=Tomatoquantity.getText().toString();
                                int CostOfOnion=35;
                                docRef.update("onionCost",String.valueOf(CostOfOnion));
                                docRef.update("onionQuantity",String.valueOf(QuantityOfOnion));


                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(VegetablesWholesaler.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });

            }
        });

    }
    public void incrementTomato(View v)
    {
        count++;
        Tomatoquantity.setText("" + count);
    }
    public void decrementTomato(View v)
    {
        if(count<=0)
            count = 0;
        else
            count--;
        Tomatoquantity.setText("" + count);
    }
    public void incrementOnion(View v)
    {
        count++;
        Onionquantity.setText("" + count);
    }
    public void decrementOnion(View v)
    {
        if(count<=0)
            count = 0;
        else
            count--;
        Onionquantity.setText("" + count);
    }
}