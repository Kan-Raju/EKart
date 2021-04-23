package com.example.trailproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;

//class Order
//{
//    public static int descending(OrderItems a, OrderItems b)
//    {
//        int x;
//        x = Double.parseDouble(a.getTimeStamp()) > Double.parseDouble(a.getTimeStamp()) ? 1:-1;
//        if(a.equals(b)) x = 0;
//        return x;
//    }
//}

public class CustomerMainActivity extends AppCompatActivity implements View.OnClickListener {
    String searched_item;// = "apple";
    int mod = 13;

    public static ArrayList<Retailer> retailers = new ArrayList<>();
    public static ArrayList<OrderItems> cartItems = new ArrayList<>();

    ArrayList<String> items = new ArrayList<>(Arrays.asList("apple","orange","tomato","onion"));

    ArrayList<RelativeLayout> layoutarr = new ArrayList<>();

    ArrayList<Button> addUpdateCartBtnArr = new ArrayList<>();
    // <TextView> inCartArr = new ArrayList<>();

    ArrayList<TextView> priceTitleArr = new ArrayList<>();
    ArrayList<Button> plsBtnArr = new ArrayList<>();
    ArrayList<Button> minusBtnArr = new ArrayList<>();
    ArrayList<TextView> countArr = new ArrayList<>();

    ArrayList<Integer> availableArr = new ArrayList<>();

    EditText search_space;
    Button search_btn;
    LinearLayout parent;

    Button myCartBtn;
    Button prevOrdsBtn;

    private int findCartItemLoc(int pos)
    {
        HashMap<String, String> hmap = new HashMap<>();
        hmap.put("cId", FirebaseAuth.getInstance().getCurrentUser().getUid());
        hmap.put("itemName",searched_item);
        int c = Integer.parseInt(countArr.get(pos).getText().toString());
        hmap.put("count",countArr.get(pos).getText().toString());
        hmap.put("sellerId",retailers.get(pos).getrId());
        hmap.put("totalPrice",Integer.toString(getCost(pos)*c));
        hmap.put("timeStamp",null);
        hmap.put("status","cart order");
        OrderItems obj = new OrderItems(hmap);
        OrderItems temp;
        for(int i=0; i<cartItems.size(); i++)
        {
            temp = cartItems.get(i);
            if(temp.equals(obj))
            {
                return i;
            }
        }
        return -1;
    }

    private void alterButton(int pos, Button btn, TextView temp)
    {
        int orderPos = findCartItemLoc(pos);

        if(orderPos == -1)
        {
            btn.setText("Add to Cart");
            btn.setAlpha(1);
            btn.setClickable(true);
        }
        else
        {
            OrderItems item = cartItems.get(orderPos);
            if(! item.getCount().equalsIgnoreCase(temp.getText().toString()))
            {
                btn.setText("Update Cart");
                btn.setAlpha(1);
                btn.setClickable(true);
            }
            else
            {
                btn.setText("Already in Cart");
                btn.setAlpha(0.5f);
                btn.setClickable(false);
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        long id = v.getId();

        int tempId = (int)(id-130);

        int pos = tempId/mod;

        if(tempId%mod == 6)  // add to cart button pressed;
        {
            Button btn = addUpdateCartBtnArr.get(pos);
            String textOnBtn = btn.getText().toString();
            if(textOnBtn.equalsIgnoreCase("Add to Cart"))
            {
                HashMap<String, String> hmap = new HashMap<>();
                hmap.put("cId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                hmap.put("itemName",searched_item);
                int c = Integer.parseInt(countArr.get(pos).getText().toString());
                hmap.put("count",countArr.get(pos).getText().toString());
                hmap.put("sellerId",retailers.get(pos).getrId());
                hmap.put("totalPrice",Integer.toString(getCost(pos)*c));
                hmap.put("timeStamp",null);
                hmap.put("status","cart order");
                OrderItems obj = new OrderItems(hmap);
                cartItems.add(obj);

                Toast.makeText(this, "added to cart successfully", Toast.LENGTH_SHORT).show();

                btn.setText("Already in Cart");
                btn.setAlpha(.5f);
                btn.setClickable(false);
            }
            if(textOnBtn.equalsIgnoreCase("Update cart"))
            {
                int orderPos = findCartItemLoc(pos);
                OrderItems item;
                if(orderPos != -1)
                {
                    item = cartItems.get(orderPos);
                    String count = countArr.get(pos).getText().toString();
                    item.setCount(count);
                    int c = Integer.parseInt(count);
                    item.setTotalPrice(Integer.toString(getCost(pos)*c));

                    if(c == 0) cartItems.remove(orderPos);

                    Toast.makeText(this, "updated cart successfully", Toast.LENGTH_SHORT).show();

                    btn.setText("Already in Cart");
                    btn.setAlpha(.5f);
                    btn.setClickable(false);
                }
                else
                {
                    btn.setText("Add to Cart");
                    btn.setAlpha(1);
                    btn.callOnClick();
                }

            }
        }
        else if(tempId%mod == 9)
        {
            TextView temp = countArr.get(pos);
            Button btn = addUpdateCartBtnArr.get(pos);
            OrderItems item;

            int curr = Integer.parseInt(temp.getText().toString());
            if(curr > 0) {
                curr--;
                temp.setText(Integer.toString(curr));
                int price = getCost(pos);
                priceTitleArr.get(pos).setText("Rs. " + Integer.toString(price*curr) + " / " + curr +" pcs.");

                alterButton(pos, btn, temp);
            }
        }
        else if(tempId%mod == 11)
        {
            TextView temp = countArr.get(pos);
            Button btn = addUpdateCartBtnArr.get(pos);
            OrderItems item;

            int curr = Integer.parseInt(temp.getText().toString());
            if(curr < availableArr.get(pos)) {
                ++curr;
                temp.setText(Integer.toString(curr));
                int price = getCost(pos);
                priceTitleArr.get(pos).setText("Rs. " + Integer.toString(price * curr) + " / " + curr + " pcs.");

                alterButton(pos, btn, temp);
            }
        }

    }

    private void setListenersToAllButtons(Context context)
    {
        for(Button addUpdateCartBtn:addUpdateCartBtnArr) addUpdateCartBtn.setOnClickListener(this);
        for(Button delBut:plsBtnArr) delBut.setOnClickListener(this);
        for(Button playBut:minusBtnArr) playBut.setOnClickListener(this);
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
    private int getCost(int pos)
    {
        switch(searched_item) {
            case "apple":
                return retailers.get(pos).getAppC();
            case "orange":
                return retailers.get(pos).getOrngC();
            case "tomato":
                return retailers.get(pos).getTmtC();
            case "onion":
                return retailers.get(pos).getOninC();
            default:
                return 0;
        }
    }

    private RelativeLayout getLayout(Context context, int pos)
    {
        RelativeLayout layout = new RelativeLayout(context);
        int ht = (int) (200 * this.getResources().getDisplayMetrics().density + 0.5f);
        try {
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ht));
            layout.setBackgroundColor(Color.WHITE);
            layout.setId(mod * pos + 1 + 130);

            ImageView photo = new ImageView(this);
            photo.setId(mod*pos + 2 + 130);
            setImage(photo);
            RelativeLayout.LayoutParams photoParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            photoParam.leftMargin = 50;
            photoParam.topMargin = 100;
            photoParam.width = 300;
            photoParam.height = 300;
            photo.setLayoutParams(photoParam);


            TextView itemName = new TextView(this);
            itemName.setId(mod * pos + 3 + 130);
            itemName.setText(searched_item);
            itemName.setTextSize(24);
            RelativeLayout.LayoutParams itemNameParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            itemNameParam.leftMargin = 370;
            itemNameParam.topMargin = 50;
            itemNameParam.width = 800;
            itemNameParam.height = 200;
            itemName.setLayoutParams(itemNameParam);

            TextView price_title = new TextView(this);
            price_title.setId(mod * pos + 4 + 130);
            price_title.setText("Rs. " + getCost(pos) + " / " +"1 pc.");
            price_title.setTextSize(20);
            RelativeLayout.LayoutParams price_titleParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            price_titleParam.leftMargin = 370;
            price_titleParam.topMargin = 190;
            price_titleParam.width = 1000;
            price_titleParam.height = 200;
            price_title.setLayoutParams(price_titleParam);

            TextView available = new TextView(this);
            available.setId(mod * pos + 5 + 130);
            available.setText("max pcs: " + getCount(pos));
            available.setTextSize(20);
            RelativeLayout.LayoutParams availableParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            availableParam.leftMargin = 370;
            availableParam.topMargin = 330;
            availableParam.width = 1000;
            availableParam.height = 200;
            available.setLayoutParams(availableParam);


            Button addUpdateCart = new Button(this);
            addUpdateCart.setId(mod * pos + 6 + 130);
            addUpdateCart.setText("Add to Cart");
            addUpdateCart.setTextSize(10);
            addUpdateCart.setTextColor(Color.BLACK);
            addUpdateCart.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams addUpdateCartParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            addUpdateCartParam.leftMargin = 950;
            addUpdateCartParam.topMargin = 90;
            addUpdateCartParam.width = 400;
            addUpdateCartParam.height = 120;
            addUpdateCart.setLayoutParams(addUpdateCartParam);

//            Button updateCart = new Button(this);
//            updateCart.setId(mod * pos + 7);
//            updateCart.setText("update Cart");
//            updateCart.setTextSize(10);
//            updateCart.setTextColor(Color.BLACK);
//            updateCart.setBackgroundColor(Color.WHITE);
//            RelativeLayout.LayoutParams updateCartParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
//            updateCartParam.leftMargin = 950;
//            updateCartParam.topMargin = 90;
//            updateCartParam.width = 400;
//            updateCartParam.height = 120;
//            updateCart.setLayoutParams(updateCartParam);
//            updateCart.setVisibility(GONE);


//            TextView inCart = new Button(this);
//            inCart.setId(mod * pos + 8 + 130);
//            inCart.setText("In Cart");
//            inCart.setTextSize(10);
//            inCart.setTextColor(Color.BLACK);
//            inCart.setBackgroundColor(Color.WHITE);
//            RelativeLayout.LayoutParams inCartParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
//            inCartParam.leftMargin = 950;
//            inCartParam.topMargin = 90;
//            inCartParam.width = 400;
//            inCartParam.height = 120;
//            inCart.setLayoutParams(inCartParam);
//            inCart.setVisibility(GONE);


            Button minusBtn = new Button(this);
            minusBtn.setId(mod*pos + 9 + 130);
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
            count.setId(mod * pos + 10 + 130);
            count.setText("0");
            count.setTextSize(18);
            RelativeLayout.LayoutParams countParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            countParam.leftMargin = 1100;
            countParam.topMargin = 250;
            countParam.width = 100;
            countParam.height = 100;
            count.setLayoutParams(countParam);


            Button plusBtn = new Button(this);
            plusBtn.setId(mod*pos + 11 + 130);
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
            seller.setId(mod * pos + 12 + 130);
            seller.setText("Seller : "+retailers.get(pos).getName()+" \n "+"           "+retailers.get(pos).getCity());
            seller.setTextSize(18);
            RelativeLayout.LayoutParams sellerParam = new RelativeLayout.LayoutParams(ht - 1, ht - 1);
            sellerParam.leftMargin = 80;
            sellerParam.topMargin = 450;
            sellerParam.width = 1500;
            sellerParam.height = 300;
            seller.setLayoutParams(sellerParam);

            layoutarr.add(layout);
            addUpdateCartBtnArr.add(addUpdateCart);
            // inCartArr.add(inCart);

            priceTitleArr.add(price_title);

            plsBtnArr.add(plusBtn);
            minusBtnArr.add(minusBtn);
            countArr.add(count);

            String f = available.getText().toString();
            availableArr.add(Integer.parseInt(f.substring(9)));

            layout.addView(photo);

            layout.addView(itemName);
            layout.addView(price_title);
            layout.addView(available);

            layout.addView(addUpdateCart);
            // layout.addView(updateCart);
            // layout.addView(inCart);

            layout.addView(minusBtn);
            layout.addView(count);
            layout.addView(plusBtn);

            layout.addView(seller);


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

                                    try
                                    {
                                        Retailer r = new Retailer(hmap);
                                        //if(!retailers.contains(r))
                                        retailers.add(r);
                                    }catch(Exception e)
                                    {
                                        Toast.makeText(CustomerMainActivity.this, "error :"+e.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
//                                else
//                                {
//                                    Toast.makeText(CustomerMainActivity.this, document.getString("userType"), Toast.LENGTH_LONG).show();
//                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(CustomerMainActivity.this, "task failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void clearFrontEnd()
    {
        // delete all Layouts
        for(RelativeLayout layout:layoutarr)
            parent.removeView(layout);

        //clear in all arrays
        layoutarr = new ArrayList<>();
        addUpdateCartBtnArr = new ArrayList<>();
        // inCartArr = new ArrayList<>();
        plsBtnArr = new ArrayList<>();
        minusBtnArr = new ArrayList<>();
        countArr = new ArrayList<>();
        availableArr = new ArrayList<>();
        priceTitleArr = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        search_space = findViewById(R.id.search_space);
        search_btn = findViewById(R.id.search_btn);

        prevOrdsBtn = findViewById(R.id.previousorders);
        myCartBtn = findViewById(R.id.mycartbtn);

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
                    setListenersToAllButtons(CustomerMainActivity.this);
                }
                else
                {
                    clearFrontEnd();
                    search_space.setError("item not available");
                }
            }
        });


        prevOrdsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), CustomerOldOrders.class));
            }
        });


        myCartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), CustomerCart.class));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        clearFrontEnd();
        retailers = new ArrayList<>();
        finish();
    }

}