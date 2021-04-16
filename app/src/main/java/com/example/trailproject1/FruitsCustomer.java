package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FruitsCustomer extends AppCompatActivity {

    TextView applequantity,orangequantity;
    int countApple,countOrange = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_customer);

        applequantity = (TextView) findViewById(R.id.CustomerAppleQuantityValue);
        orangequantity = (TextView) findViewById(R.id.CustomerOrangeQuantityValue);

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

/*
package com.example.allpagesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FMC extends AppCompatActivity {

    TextView applequantity,orangequantity,bananaquantity;
    int countApple,countOrange,countBanana = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_m_c);

        applequantity = (TextView) findViewById(R.id.AppleQuantityValue);
        orangequantity = (TextView) findViewById(R.id.OrangeQuantityValue);
        bananaquantity = (TextView) findViewById(R.id.BananaQuantityValue);
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
    public void incrementbanana(View v)
    {
        countBanana++;
        bananaquantity.setText("" + countBanana);
    }
    public void decrementbanana(View v)
    {
        if(countBanana<=0)
            countBanana = 0;
        else
            countBanana--;
        bananaquantity.setText("" + countBanana);
    }
}*/