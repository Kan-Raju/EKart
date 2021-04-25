package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.trailproject1.CustomerMainActivity.cartItems;

public class CustomerPaymentOptions extends AppCompatActivity
{
    Button placeOrderBtn;
    String userId;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    DocumentReference docRef;

    private void gotoFinalPage()
    {
        startActivity(new Intent(getApplicationContext(), CustomerFinalPage.class));
    }

    private void pushCartItemsToFirebasePlacedOrdersAndGotoFinalPage()
    {
        gotoFinalPage();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment_options);

        userId=firebaseAuth.getCurrentUser().getUid();
        docRef= fStore.collection("PlacedButNotOTWOrders").document(userId);


        placeOrderBtn = findViewById(R.id.place_order_btn);
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCartItemsToFirebasePlacedOrdersAndGotoFinalPage();
                // startActivity(new Intent(getApplicationContext(), CustomerFinalPage.class));
            }
        });
    }
}