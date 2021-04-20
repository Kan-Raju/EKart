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
import android.widget.ImageButton;
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


public class CustomerFruitAppleAddToCartPage extends AppCompatActivity implements View.OnClickListener
{
    String item = "Apple";
    int mod = 3;

    ArrayList<Retailer> retailers;
    ArrayList<RelativeLayout> layoutarr = new ArrayList<>();
    ArrayList<Button> addToCartBtnArr = new ArrayList<>();
    ArrayList<Button> plsBtnArr = new ArrayList<>();
    ArrayList<Button> minusBtnArr = new ArrayList<>();
    ArrayList<TextView> countArr = new ArrayList<>();
    TextView search_space;
    Button search_btn;



    @Override
    public void onClick(View v)
    {


    }
    private void setListenersToAllButtons(Context context)
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
            addToCart.setText(retailers.get(pos)+" Cart");
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
    private void displayAllRetailers(LinearLayout parent)
    {
        RelativeLayout inner;
        for(int retailer_pos=0; retailer_pos < retailers.size(); retailer_pos++)
        {
            inner=getLayout(this,retailer_pos);
            parent.addView(inner);
        }
    }

    private ArrayList<Retailer> getAllRetailersFromFirebase(Context context)
    {
        ArrayList<Retailer> soln = new ArrayList<Retailer>();
        final boolean[] flag = {false};
        FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                if(document.getString("userType").equals("retailer")) {
                                    HashMap<String, String> hmap = new HashMap<>();
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

                                    Toast.makeText(context,hmap.toString(),Toast.LENGTH_LONG).show();

                                    soln.add(new Retailer(hmap));
                                }
                                else
                                {
                                    Toast.makeText(context,document.getString("userType"),Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(context,"task failed",Toast.LENGTH_LONG).show();
                        }
                        flag[0] = true;
                    }
                });

        //while(!flag[0]);
        return soln;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_fruit_apple_add_to_cart_page);
        search_space = findViewById(R.id.search_space);
        search_btn =findViewById( R.id.search_btn);

        LinearLayout parent = findViewById(R.id.outer_layout);


//        NewThread nt= new NewThread();
//        try {
//            nt.t.start();
//        }
//        catch(Exception e) {
//            Toast.makeText(this, "err = " + e.toString(), Toast.LENGTH_SHORT).show();
//        }

        try {
            retailers = getAllRetailersFromFirebase(this);
            String x = retailers.get(0).toHashMap().toString();
            Toast.makeText(this,"x = " + x, Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();
        }

        //retailers = new ArrayList<>(Arrays.asList("1","2"));
        // displayAllRetailers(parent);
        //setListenersToAllButtons(this);
    }
}

class NewThread implements java.lang.Runnable
{
    Thread t;

    public NewThread() {
        t = new Thread(this, "seekBarThread");
    }

    public void run()
    {
        try
        {

        }
        catch(Exception ignored){}
    }
}
