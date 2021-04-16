package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FruitsRetailer extends AppCompatActivity {

    TextView applequantity,orangequantity;
    int countApple,countOrange = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_retailer);
        applequantity=(TextView)findViewById(R.id.RetailerAppleQuantityValue);
        orangequantity = (TextView) findViewById(R.id.RetailerOrangeQuantityValue);

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