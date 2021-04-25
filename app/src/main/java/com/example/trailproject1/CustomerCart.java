package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.trailproject1.CustomerMainActivity.cartItems;
import static com.example.trailproject1.CustomerMainActivity.retailers;

public class CustomerCart extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<RelativeLayout> layoutArr = new ArrayList<>();
    ArrayList<ImageView> photoArr = new ArrayList<>();
    ArrayList<TextView> itemNameArr = new ArrayList<>();
    ArrayList<TextView> countArr = new ArrayList<>();
    ArrayList<TextView> totalPriceArr = new ArrayList<>();
    ArrayList<Button> removeFromCartBtnArr = new ArrayList<>();
    ArrayList<TextView> sellerArr = new ArrayList<>();

    LinearLayout parent;
    Button oldOrdBtn, placeOrderBtn;
    int mod = 9;
    int constant = 200;

    private void clearAllArraysAndUpdateIdsofAllObjects(int pos)
    {
        layoutArr.remove(pos);
        photoArr.remove(pos);
        itemNameArr.remove(pos);
        countArr.remove(pos);
        totalPriceArr.remove(pos);
        removeFromCartBtnArr.remove(pos);
        sellerArr.remove(pos);

        for(int i=pos; i<layoutArr.size(); i++)
        {
            layoutArr.get(i).setId(layoutArr.get(i).getId() - mod);
            photoArr.get(i).setId(photoArr.get(i).getId() - mod);
            itemNameArr.get(i).setId(itemNameArr.get(i).getId() - mod);
            totalPriceArr.get(i).setId(totalPriceArr.get(i).getId() - mod);
            removeFromCartBtnArr.get(i).setId(removeFromCartBtnArr.get(i).getId() - mod);
            sellerArr.get(i).setId(sellerArr.get(i).getId() - mod);
        }
    }

    @Override
    public void onClick(View v)
    {
        long id = v.getId();

        int tempId = (int)(id-constant);

        int pos = tempId/mod;

        if(tempId%mod == 6)  // remove from cart button pressed;
        {
            cartItems.remove(pos);
            RelativeLayout inner = layoutArr.get(pos);
            parent.removeView(inner);
            clearAllArraysAndUpdateIdsofAllObjects(pos);
        }
    }
    private void setListenersToAllButtons()
    {
        for(Button removeFromCartBtn:removeFromCartBtnArr) removeFromCartBtn.setOnClickListener(this);
    }
    private String getSeller(int pos)
    {
        String sId = cartItems.get(pos).getSellerId();
        for(int i=0; i<retailers.size(); i++)
        {
            if(retailers.get(i).getrId().equals(sId))
            {
                String name = retailers.get(i).getName();
                String city = retailers.get(i).getCity();
                return "Seller : "+name+" \n "+"           "+city;
            }
        }
        return "Seller : Error Loading !";
    }
    private void setImage(ImageView img,int pos)
    {
        switch(cartItems.get(pos).getItemName()){
            case "apple":
                img.setImageResource(R.drawable.applefruit);
                break;
            case "orange":
                img.setImageResource(R.drawable.orangefruit);
                break;
            case "tomato":
                img.setImageResource(R.drawable.tomatovegetable);
                break;
            case "onion":
                img.setImageResource(R.drawable.onionvegetable);
                break;
            default:
                img.setImageResource(R.drawable.bananafruit);
        }
    }
    private int x(double x)
    {
        return (int) (x * this.getResources().getDisplayMetrics().widthPixels);
    }

    private RelativeLayout getLayout(Context context, int pos)
    {
        RelativeLayout layout = new RelativeLayout(context);
        int ht = (int) (250 * this.getResources().getDisplayMetrics().density + 0.5f);
        try {
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ht));
            layout.setBackgroundColor(Color.WHITE);
            layout.setId(mod * pos + 1 + constant);

            ImageView photo = new ImageView(this);
            photo.setId(mod*pos + 2 + constant);
            setImage(photo, pos);
            RelativeLayout.LayoutParams photoParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            photoParam.leftMargin = x(50.0/1440);
            photoParam.topMargin = 100;
            photoParam.width = x(300.0/1440);
            photoParam.height = x(300.0/1440);
            photo.setLayoutParams(photoParam);

            TextView itemName = new TextView(this);
            itemName.setId(mod * pos + 3 + 130);
            itemName.setText(cartItems.get(pos).getItemName().toUpperCase());
            itemName.setTextSize(32);
            RelativeLayout.LayoutParams itemNameParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            itemNameParam.leftMargin = x(370.0/1440);
            itemNameParam.topMargin = 50;
            itemNameParam.width = x(800.0/1440);
            itemNameParam.height = 200;
            itemName.setLayoutParams(itemNameParam);

            TextView count = new TextView(this);
            count.setId(mod * pos + 4 + constant);
            count.setText("count : " + cartItems.get(pos).getCount());
            count.setTextSize(18);
            RelativeLayout.LayoutParams countParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            countParam.leftMargin = x(370.0/1440);
            countParam.topMargin = 250;
            countParam.width = x(500.0/1440);
            countParam.height = 100;
            count.setLayoutParams(countParam);

            TextView totalPrice = new TextView(this);
            totalPrice.setId(mod * pos + 5 + constant);
            totalPrice.setText("total price: " + cartItems.get(pos).getTotalPrice());
            totalPrice.setTextSize(20);
            RelativeLayout.LayoutParams availableParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            availableParam.leftMargin = x(370.0/1440);
            availableParam.topMargin = 330;
            availableParam.width = x(1000.0/1440);
            availableParam.height = 200;
            totalPrice.setLayoutParams(availableParam);

            Button removeFromCartBtn = new Button(this);
            removeFromCartBtn.setId(mod * pos + 6 + constant);
            removeFromCartBtn.setText("Remove from  Cart");
            removeFromCartBtn.setTextSize(10);
            removeFromCartBtn.setTextColor(Color.BLACK);
            removeFromCartBtn.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams removeFromCartBtnParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            removeFromCartBtnParam.leftMargin = x(3.4/5.0);
            removeFromCartBtnParam.topMargin = 90;
            removeFromCartBtnParam.width = x(500.0/1440);
            removeFromCartBtnParam.height = 120;
            removeFromCartBtn.setLayoutParams(removeFromCartBtnParam);


            TextView seller = new TextView(this);
            seller.setId(mod * pos + 7 + constant);
            seller.setText(getSeller(pos));
            seller.setTextSize(18);
            RelativeLayout.LayoutParams sellerParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            sellerParam.leftMargin = x(80.0/1440);
            sellerParam.topMargin = 450;
            sellerParam.width = x(1300.0/1440);
            sellerParam.height = 300;
            seller.setLayoutParams(sellerParam);

            layoutArr.add(layout);
            photoArr.add(photo);
            itemNameArr.add(itemName);
            countArr.add(count);
            totalPriceArr.add(totalPrice);
            removeFromCartBtnArr.add(removeFromCartBtn);
            sellerArr.add(seller);


            layout.addView(photo);
            layout.addView(itemName);
            layout.addView(totalPrice);
            layout.addView(count);

            layout.addView(removeFromCartBtn);
            layout.addView(seller);


            //setContentView(layout);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return layout;
    }





    private void displayAllCartItems()
    {
        // Toast.makeText(this,"no. of items in cart : " + cartItems.size(),Toast.LENGTH_LONG).show();
        RelativeLayout inner;
        for (int item_pos = 0; item_pos < cartItems.size(); item_pos++)
        {
                inner = getLayout(this, item_pos);
                parent.addView(inner);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        parent = findViewById(R.id.outer_layout);
        oldOrdBtn = findViewById(R.id.old_orders);
        placeOrderBtn = findViewById(R.id.place_order);

        oldOrdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomerOldOrders.class));
            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomerPaymentOptions.class));
            }
        });
        displayAllCartItems();
        setListenersToAllButtons();
    }

}