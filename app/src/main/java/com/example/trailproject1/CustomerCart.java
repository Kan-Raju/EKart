package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.trailproject1.CustomerMainActivity.cartItems;

public class CustomerCart extends AppCompatActivity implements View.OnClickListener
{
    LinearLayout parent;
    Button oldOrdBtn, placeOrderBtn;
    @Override
    public void onClick(View v)
    {

    }

    private void setListenersToAllButtons()
    {

    }

    private void displayAllCartItems()
    {
        Toast.makeText(this,"no. of items in cart : " + cartItems.size(),Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        parent = findViewById(R.id.outer_layout);
        oldOrdBtn = findViewById(R.id.old_orders);
        placeOrderBtn = findViewById(R.id.place_order);

        oldOrdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomerOldOrders.class));
            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomerPaymentOptions.class));
            }
        });
        displayAllCartItems();
        setListenersToAllButtons();
    }

}