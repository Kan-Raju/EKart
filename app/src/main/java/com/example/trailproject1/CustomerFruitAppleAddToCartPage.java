package com.example.trailproject1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

import static android.view.View.GONE;
import static android.view.View.TEXT_ALIGNMENT_TEXT_START;


public class CustomerFruitAppleAddToCartPage extends AppCompatActivity implements View.OnClickListener
{
    String item = "Apple";
    int mod = 3;
    ArrayList<String> vendors;
    ArrayList<RelativeLayout> layoutarr = new ArrayList<>();
    ArrayList<Button> addToCartBtnArr = new ArrayList<>();
    ArrayList<TextView> itemNamearr = new ArrayList<>();


    @Override
    public void onClick(View v)
    {

    }
    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private RelativeLayout getLayout(Context context, int pos)
    {
        RelativeLayout layout = new RelativeLayout(context);
        int ht = (int) (200 * this.getResources().getDisplayMetrics().density + 0.5f);
        try
        {
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ht));
            layout.setBackgroundColor(Color.GREEN);
            layout.setId(mod*pos + 1);

            Button addToCart = new Button(this);
            addToCart.setId(mod*pos+2);
            addToCart.setText(vendors.get(pos)+"dd to Cart");
            addToCart.setTextColor(Color.BLACK);
            addToCart.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams addToCartParam = new RelativeLayout.LayoutParams(ht-1, ht-1);
            addToCartParam.leftMargin = 900;
            addToCartParam.topMargin = 100;
            addToCartParam.width = 450;
            addToCartParam.height = 150;
            addToCart.setLayoutParams(addToCartParam);


            TextView itemName = new TextView(this);
            itemName.setId(mod*pos + 3);
            itemName.setText(item);
            itemName.setTextSize(24);
            RelativeLayout.LayoutParams itemNameParam = new RelativeLayout.LayoutParams(ht-1, ht-1);
            itemNameParam.leftMargin = 90;
            itemNameParam.topMargin = 50;
            itemNameParam.width = 800;
            itemNameParam.height = 200;
            itemName.setLayoutParams(itemNameParam);

            int value = 400;
            int nItems = 10;

            TextView price_title = new TextView(this);
            price_title.setId(mod*pos + 4);
            price_title.setText("Rs. " + Integer.toString(value*nItems) + " / " + Integer.toString(nItems) + "pcs.");
            price_title.setTextSize(20);
            RelativeLayout.LayoutParams price_titleParam = new RelativeLayout.LayoutParams(ht-1, ht-1);
            price_titleParam.leftMargin = 100;
            price_titleParam.topMargin = 200;
            price_titleParam.width = 1000;
            price_titleParam.height = 200;
            price_title.setLayoutParams(price_titleParam);


            TextView seller = new TextView(this);
            seller.setId(mod*pos + 4);
            seller.setText("Seller : More Super Market, Jio Stores \n            delhi.");
            seller.setTextSize(18);
            RelativeLayout.LayoutParams sellerParam = new RelativeLayout.LayoutParams(ht-1, ht-1);
            sellerParam.leftMargin = 100;
            sellerParam.topMargin = 350;
            sellerParam.width = 1500;
            sellerParam.height = 300;
            seller.setLayoutParams(sellerParam);


            layoutarr.add(layout);
            addToCartBtnArr.add(addToCart);
            itemNamearr.add(itemName);

            layout.addView(addToCart);
            layout.addView(itemName);
            layout.addView(price_title);
            layout.addView(seller);

            //setContentView(layout);
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
        return layout;
    }
    private void displayAllVendors(LinearLayout parent)
    {
        RelativeLayout inner;
        for(int vendor_pos=0; vendor_pos < vendors.size(); vendor_pos++)
        {
            inner=getLayout(this,vendor_pos);
            parent.addView(inner);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_fruit_apple_add_to_cart_page);

        LinearLayout parent = findViewById(R.id.outer_layout);
        vendors = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9")); //ideally this should be list of vendor objects.

        displayAllVendors(parent);

    }


}