package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuWholesaler extends AppCompatActivity {

    private Button WholesalerMainmenuFruitMenuBtn,WholesalerMainmenuVegetableMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_wholesaler);

        WholesalerMainmenuFruitMenuBtn=(Button)findViewById(R.id.WholesalerMainMenuFruitsButton);
        WholesalerMainmenuVegetableMenuBtn=(Button)findViewById(R.id.WholesalerMainMenuVegetablesButton);


        WholesalerMainmenuFruitMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuWholesaler.this,FruitsWholesaler.class);
                startActivity(intent);
            }
        });
        WholesalerMainmenuVegetableMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenuWholesaler.this,VegetablesWholesaler.class);
                startActivity(intent);
            }
        });


    }
}