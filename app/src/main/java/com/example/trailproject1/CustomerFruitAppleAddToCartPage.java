package com.example.trailproject1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

import static android.view.View.GONE;
import static android.view.View.TEXT_ALIGNMENT_TEXT_START;


public class CustomerFruitAppleAddToCartPage extends AppCompatActivity implements View.OnClickListener {
    String searched_item = "apple";
    int mod = 12;

    public static ArrayList<Retailer> retailers = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>(Arrays.asList("apple","orange","tomato","onion"));
    ArrayList<RelativeLayout> layoutarr = new ArrayList<>();
    ArrayList<Button> addToCartBtnArr = new ArrayList<>();
    ArrayList<Button> plsBtnArr = new ArrayList<>();
    ArrayList<Button> minusBtnArr = new ArrayList<>();
    ArrayList<TextView> countArr = new ArrayList<>();
    EditText search_space;
    Button search_btn;
    LinearLayout parent;


    @Override
    public void onClick(View v) {


    }

    private void setListenersToAllButtons(Context context) {

    }
    private String getCount(int pos)
    {
        switch (searched_item) {
            case "apple":
                return Integer.toString(retailers.get(pos).getAppQ());
            case "orange":
                return Integer.toString(retailers.get(pos).getOrngQ());
            case "tomato":
                return Integer.toString(retailers.get(pos).getTmtQ());
            case "onion":
                return Integer.toString(retailers.get(pos).getOninQ());
            default:
                return "0";
        }
    }

    private void setImage(ImageView img)
    {
        switch(searched_item){
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

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private RelativeLayout getLayout(Context context, int pos)
    {
        RelativeLayout layout = new RelativeLayout(context);
        int ht = (int) (200 * this.getResources().getDisplayMetrics().density + 0.5f);
        try {
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ht));
            layout.setBackgroundColor(Color.WHITE);
            layout.setId(mod * pos + 1);

            ImageView photo = new ImageView(this);
            photo.setId(mod*pos + 2);
            setImage(photo);
            RelativeLayout.LayoutParams photoParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            photoParam.leftMargin = 50;
            photoParam.topMargin = 100;
            photoParam.width = 300;
            photoParam.height = 300;
            photo.setLayoutParams(photoParam);


            TextView itemName = new TextView(this);
            itemName.setId(mod * pos + 3);
            itemName.setText(searched_item);
            itemName.setTextSize(24);
            RelativeLayout.LayoutParams itemNameParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            itemNameParam.leftMargin = 370;
            itemNameParam.topMargin = 50;
            itemNameParam.width = 800;
            itemNameParam.height = 200;
            itemName.setLayoutParams(itemNameParam);

            int value = 400;
            int nItems = 10;

            TextView price_title = new TextView(this);
            price_title.setId(mod * pos + 4);
            price_title.setText("Rs. " + Integer.toString(value * nItems) + " / " + Integer.toString(nItems) + "pcs.");
            price_title.setTextSize(20);
            RelativeLayout.LayoutParams price_titleParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            price_titleParam.leftMargin = 370;
            price_titleParam.topMargin = 190;
            price_titleParam.width = 1000;
            price_titleParam.height = 200;
            price_title.setLayoutParams(price_titleParam);

            TextView available = new TextView(this);
            available.setId(mod * pos + 4);
            available.setText("max pcs: " + getCount(pos));
            available.setTextSize(20);
            RelativeLayout.LayoutParams availableParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            availableParam.leftMargin = 370;
            availableParam.topMargin = 330;
            availableParam.width = 1000;
            availableParam.height = 200;
            available.setLayoutParams(availableParam);


            Button addToCart = new Button(this);
            addToCart.setId(mod * pos + 5);
            addToCart.setText("Add to Cart");
            addToCart.setTextSize(10);
            addToCart.setTextColor(Color.BLACK);
            addToCart.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams addToCartParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            addToCartParam.leftMargin = 950;
            addToCartParam.topMargin = 90;
            addToCartParam.width = 400;
            addToCartParam.height = 120;
            addToCart.setLayoutParams(addToCartParam);


            Button minusBtn = new Button(this);
            minusBtn.setId(mod*pos + 6);
            minusBtn.setText(" - ");
            minusBtn.setTextSize(10);
            minusBtn.setTextColor(Color.BLACK);
            minusBtn.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams minusBtnParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            minusBtnParam.leftMargin = 950;
            minusBtnParam.topMargin = 250;
            minusBtnParam.width = 120;
            minusBtnParam.height = 100;
            minusBtn.setLayoutParams(minusBtnParam);

            TextView count = new TextView(this);
            count.setId(mod * pos + 9);
            count.setText("01");
            count.setTextSize(18);
            RelativeLayout.LayoutParams countParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            countParam.leftMargin = 1100;
            countParam.topMargin = 250;
            countParam.width = 100;
            countParam.height = 100;
            count.setLayoutParams(countParam);


            Button plusBtn = new Button(this);
            plusBtn.setId(mod*pos + 6);
            plusBtn.setText(" + ");
            plusBtn.setTextSize(10);
            plusBtn.setTextColor(Color.BLACK);
            plusBtn.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams plusBtnParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            plusBtnParam.leftMargin = 1200;
            plusBtnParam.topMargin = 250;
            plusBtnParam.width = 120;
            plusBtnParam.height = 100;
            plusBtn.setLayoutParams(plusBtnParam);


            TextView seller = new TextView(this);
            seller.setId(mod * pos + 9);
            seller.setText("Seller : "+retailers.get(pos).getName()+" \n "+"           "+retailers.get(pos).getCity());
            seller.setTextSize(18);
            RelativeLayout.LayoutParams sellerParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            sellerParam.leftMargin = 80;
            sellerParam.topMargin = 450;
            sellerParam.width = 1500;
            sellerParam.height = 300;
            seller.setLayoutParams(sellerParam);

            layoutarr.add(layout);
            addToCartBtnArr.add(addToCart);

            layout.addView(photo);
            layout.addView(itemName);
            layout.addView(price_title);
            layout.addView(addToCart);
            layout.addView(minusBtn);
            layout.addView(count);
            layout.addView(plusBtn);
            layout.addView(seller);
            layout.addView(available);

            //setContentView(layout);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return layout;
    }

    private boolean itemAvailable(int pos)
    {
        switch(searched_item){
            case "apple":
                return retailers.get(pos).getAppQ() > 0;
            case "orange":
                return retailers.get(pos).getOrngQ() > 0;
            case "tomato":
                return retailers.get(pos).getTmtQ() > 0;
            case "onion" :
                return retailers.get(pos).getOninQ() > 0;
            default:
                return false;
        }
    }
    private void displayAllRetailersOfSearchedItem(LinearLayout parent) {
        RelativeLayout inner;
        for (int retailer_pos = 0; retailer_pos < retailers.size(); retailer_pos++)
        {
            if(itemAvailable(retailer_pos)) {
                inner = getLayout(this, retailer_pos);
                parent.addView(inner);
            }
        }
    }

    private void getAllRetailersFromFirebaseAndFillRetailersArray(Context context)
    {
        FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getString("userType").equals("retailer")) {
                                    HashMap<String, String> hmap = new HashMap<>();
                                    hmap.put("Id",document.getId());
                                    hmap.put("name", document.getString("name"));
                                    hmap.put("city", document.getString("city"));
                                    hmap.put("email", document.getString("email"));
                                    hmap.put("appC", document.getString("appleCost"));
                                    hmap.put("appQ", document.getString("appleQuantity"));
                                    hmap.put("orngC", document.getString("orangeCost"));
                                    hmap.put("orngQ", document.getString("orangeQuantity"));
                                    hmap.put("tmtC", document.getString("tomatoCost"));
                                    hmap.put("tmtQ", document.getString("tomatoQuantity"));
                                    hmap.put("oninC", document.getString("onionCost"));
                                    hmap.put("oninQ", document.getString("onionQuantity"));

                                    Toast.makeText(CustomerFruitAppleAddToCartPage.this, hmap.toString(), Toast.LENGTH_LONG).show();
                                    try {
                                        retailers.add(new Retailer(hmap));
                                    }catch(Exception e)
                                    {
                                        Toast.makeText(CustomerFruitAppleAddToCartPage.this, "error :"+e.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(CustomerFruitAppleAddToCartPage.this, document.getString("userType"), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(CustomerFruitAppleAddToCartPage.this, "task failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void clearFrontEnd()
    {
        // deleteLayout


        //clear in all arrays


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_fruit_apple_add_to_cart_page);
        search_space = findViewById(R.id.search_space);
        search_btn = findViewById(R.id.search_btn);

        parent = findViewById(R.id.outer_layout);

        try {
            getAllRetailersFromFirebaseAndFillRetailersArray(this);
        } catch (Exception e) {
            Toast.makeText(this, "err2 = " +e.toString(), Toast.LENGTH_LONG).show();
        }

        search_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                searched_item = search_space.getText().toString();
                if(items.contains(searched_item)) {
                    clearFrontEnd();
                    displayAllRetailersOfSearchedItem(parent);
                    setListenersToAllButtons(CustomerFruitAppleAddToCartPage.this);
                }
                else
                {
                    clearFrontEnd();
                    search_space.setError("item not available");
                }
            }
        });
    }

}