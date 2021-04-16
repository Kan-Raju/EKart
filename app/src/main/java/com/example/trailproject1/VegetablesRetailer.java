package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VegetablesRetailer extends AppCompatActivity {

    TextView Tomatoquantity,Onionquantity;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables_retailer);
        Tomatoquantity = (TextView) findViewById(R.id.RetailerTomatoQuantityValue);
        Onionquantity = (TextView) findViewById(R.id.RetailerOrangeQuantityValue);
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