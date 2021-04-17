package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuRetailer extends AppCompatActivity {

    Button RetailerMainmenuFruitMenuBtn,RetailerMainmenuVegetableMenuBtn,RetailerMainmenuMyordersBtn,RetailerMainmenuMycartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_retailer);
        RetailerMainmenuFruitMenuBtn=(Button)findViewById(R.id.RetailerMainMenuFruitsButton);
        RetailerMainmenuVegetableMenuBtn=(Button)findViewById(R.id.RetailerMainMenuVegetablesButton);
        RetailerMainmenuMyordersBtn=(Button)findViewById(R.id.RetailerMainMenuMyOrdersButton);
        RetailerMainmenuMycartBtn=(Button)findViewById(R.id.RetailerMainMenuMyCartButton);

        RetailerMainmenuFruitMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuRetailer.this,FruitsRetailer.class);
                startActivity(intent);
            }
        });
        RetailerMainmenuVegetableMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuRetailer.this,VegetablesRetailer.class);
                startActivity(intent);
            }
        });
        RetailerMainmenuMyordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RetailerMainmenuMycartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}