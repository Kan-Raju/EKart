package com.example.trailproject1;

import java.util.HashMap;

public class OrderItems
{
    private String cId;
    private String itemName;
    private String count;
    private String sellerId;
    private String totalPrice;
    private String timeStamp;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public OrderItems(HashMap<String, String> hmap)
    {
        cId = hmap.get("cId");
        itemName = hmap.get("itemName");
        count = hmap.get("count");
        sellerId = hmap.get("sellerId");
        totalPrice = hmap.get("totalPrice");
        timeStamp = hmap.get("timeStamp");
        status = hmap.get("status");
    }

    public HashMap<String, String> toHmap()
    {
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("cId",cId);
        hmap.put("itemName",itemName);
        hmap.put("count",count);
        hmap.put("sellerId",sellerId);
        hmap.put("totalPrice",totalPrice);
        hmap.put("timeStamp",timeStamp);
        hmap.put("status",status);
        return hmap;
    }

    @Override
    public boolean equals(Object v) {
        try
        {
            OrderItems r = (OrderItems) v;
            if(!cId.equals(r.getcId())) return false;
            if(!itemName.equals(r.getItemName())) return false;
            //if(!count.equals(r.getCount())) return false;
            if(!sellerId.equals(r.sellerId)) return false;
            //if(!totalPrice.equals(r.getTotalPrice())) return false;
            //if(!timeStamp.equals(r.getTimeStamp())) return false;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
