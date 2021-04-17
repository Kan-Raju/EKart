package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuCustomer extends AppCompatActivity {

    Button customerMainmenuFruitMenuBtn,customerMainmenuVegetableMenuBtn,customerMainmenuMyordersBtn,customerMainmenuMycartBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_customer);

        customerMainmenuFruitMenuBtn=(Button)findViewById(R.id.CustomerMainMenuFruitsButton);
        customerMainmenuVegetableMenuBtn=(Button)findViewById(R.id.CustomerMainMenuVegetablesButton);
        customerMainmenuMyordersBtn=(Button)findViewById(R.id.CustomerMainMenuMyOrdersButton);
        customerMainmenuMycartBtn=(Button)findViewById(R.id.CustomerMainMenuMyCartButton);

        customerMainmenuFruitMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuCustomer.this,FruitsCustomer.class);
                startActivity(intent);
            }
        });

        customerMainmenuVegetableMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuCustomer.this,VegetablesCustomer.class);
                startActivity(intent);
            }
        });
        customerMainmenuMyordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        customerMainmenuMycartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}