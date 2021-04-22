package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Objects;

public class Retailer
{
    private String rId;
    private  String name;
    private String city;



    private String email;

    private int appC;
    private int appQ;

    private int orngC;
    private int orngQ;

    private int tmtC;
    private int tmtQ;

    private int oninC;
    private int oninQ;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAppC() {
        return appC;
    }

    public void setAppC(int appC) {
        this.appC = appC;
    }

    public int getAppQ() {
        return appQ;
    }

    public void setAppQ(int appQ) {
        this.appQ = appQ;
    }

    public int getOrngC() {
        return orngC;
    }

    public void setOrngC(int orngC) {
        this.orngC = orngC;
    }

    public int getOrngQ() {
        return orngQ;
    }

    public void setOrngQ(int orngQ) {
        this.orngQ = orngQ;
    }

    public int getTmtC() {
        return tmtC;
    }

    public void setTmtC(int tmtC) {
        this.tmtC = tmtC;
    }

    public int getTmtQ() {
        return tmtQ;
    }

    public void setTmtQ(int tmtQ) {
        this.tmtQ = tmtQ;
    }

    public int getOninC() {
        return oninC;
    }

    public void setOninC(int oninC) {
        this.oninC = oninC;
    }

    public int getOninQ() {
        return oninQ;
    }

    public void setOninQ(int oninQ) {
        this.oninQ = oninQ;
    }

    public Retailer(HashMap<String,String> dict)
    {
        this.rId = dict.get("Id");
        this.name = dict.get("name");
        this.city = dict.get("city");
        this.email = dict.get("email");

        this.appC = Integer.parseInt(Objects.requireNonNull(dict.get("appC")));
        this.appQ = Integer.parseInt(Objects.requireNonNull(dict.get("appQ")));

        this.orngC = Integer.parseInt(Objects.requireNonNull(dict.get("orngC")));
        this.orngQ = Integer.parseInt(Objects.requireNonNull(dict.get("orngQ")));

        this.tmtC = Integer.parseInt(Objects.requireNonNull(dict.get("tmtC")));
        this.tmtQ = Integer.parseInt(Objects.requireNonNull(dict.get("tmtQ")));

        this.oninC = Integer.parseInt(Objects.requireNonNull(dict.get("oninC")));
        this.oninQ = Integer.parseInt(Objects.requireNonNull(dict.get("oninQ")));
    }

    public HashMap<String,String> toHashMap()
    {
        HashMap<String,String> hmap = new HashMap<>();
        hmap.put("Id", this.rId);
        hmap.put("name",this.name);
        hmap.put("city",this.city);
        hmap.put("email",this.email);

        hmap.put("appC",Integer.toString(this.appC));
        hmap.put("appQ",Integer.toString(this.appQ));

        hmap.put("orngC",Integer.toString(this.orngC));
        hmap.put("orngQ",Integer.toString(this.orngQ));

        hmap.put("tmtC",Integer.toString(this.tmtC));
        hmap.put("tmtQ",Integer.toString(this.tmtQ));

        hmap.put("oninC",Integer.toString(this.oninC));
        hmap.put("oninQ",Integer.toString(this.oninQ));

        return hmap;
    }

    @Override
    public boolean equals(Object v) {
        try
        {
            Retailer r = (Retailer) v;
            return rId.equals(r.getrId());
        }
        catch(Exception e)
        {
            return false;
        }
    }
}