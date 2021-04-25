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

public class VegetablesRetailer extends AppCompatActivity {

    TextView Tomatoquantity,Onionquantity;
    int count = 0;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button RetailerTomatoSelectBtn,RetailerOnionSelectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_retailer);
        Tomatoquantity = (TextView) findViewById(R.id.RetailerTomatoQuantityValue);
        Onionquantity = (TextView) findViewById(R.id.RetailerOrangeQuantityValue);

        RetailerTomatoSelectBtn=(Button)findViewById(R.id.RetailerTomatoQauntitySelectButton);
        RetailerOnionSelectBtn=(Button)findViewById(R.id.RetailerOnionQauntitySelectButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        RetailerTomatoSelectBtn.setOnClickListener(new View.OnClickListener() {
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
                                String QuantityOfApple=Tomatoquantity.getText().toString();
                                docRef.update("tomatoQuantity",String.valueOf(QuantityOfApple));


                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(VegetablesRetailer.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });
                Intent intent=new Intent(VegetablesRetailer.this,RetailerVegetableTomatoAddToCartPage.class);
                startActivity(intent);
            }
        });
        RetailerOnionSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VegetablesRetailer.this,"Out of stock ",Toast.LENGTH_SHORT).show();
            }
        });
        /*RetailerTomatoSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VegetablesRetailer.this,RetailerVegetableTomatoAddToCartPage.class);
                startActivity(intent);
            }
        });*/
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

/*package com.example.allpagesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class VMC extends AppCompatActivity {

    ,Carrotquantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_m_c);


        Carrotquantity = (TextView) findViewById(R.id.BananaQuantityValue);
    }


    public void incrementcarrot(View v)
    {
        count++;
        Carrotquantity.setText("" + count);
    }
    public void decrementcarrot(View v)
    {
        if(count<=0)
            count = 0;
        else
            count--;
        Carrotquantity.setText("" + count);
    }
}*/