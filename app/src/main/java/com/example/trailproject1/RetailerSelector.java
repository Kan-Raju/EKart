package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RetailerSelector extends AppCompatActivity {

    Button RetailerWholesalerSelectBtn,RetailerCustomerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_selector);

        RetailerWholesalerSelectBtn=findViewById(R.id.RetailerSelectorBuyFromWholesaler);
        RetailerCustomerBtn=findViewById(R.id.RetailerSelectorCustomerOrders);

        RetailerWholesalerSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RetailerSelector.this,MainMenuRetailer.class);
                startActivity(intent);
            }
        });
        RetailerCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RetailerSelector.this,RetailerCustomerMainActivity.class);
                startActivity(intent);
            }
        });

    }
}