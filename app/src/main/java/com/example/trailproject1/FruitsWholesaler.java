package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FruitsWholesaler extends AppCompatActivity {

    TextView applequantity,orangequantity;
    int countApple,countOrange = 0;
    Button WholesalerAppleSelectBtn,WholesalerOrangeSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_wholesaler);

    }
}