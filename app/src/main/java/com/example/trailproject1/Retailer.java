package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Retailer
{
    private  String rName;
    private int app_q;
    private int org_q;
    private String rCity;
    private int tomato_q;
    private int onion_q;



    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}