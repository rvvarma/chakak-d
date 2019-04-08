package com.inducesmile.androidmusicplayer.entities;

public class order_details_object {

    private String name;
    private String qty;


    private String payments;
    private String totalprice;
    private String time;
    private Integer itemcount;
    private  String orderid;
    private String phone;
    private String username;
    private  String status;
//String Username,Integer itemcount,String status,String totalprice,String payment,String time,String phone
    public order_details_object(String name, String qty) {
        this.name = name;
        this.qty = qty;
        /*this.totalprice=totalprice;
        this.phone=phone;
        this.username=Username;
        this.itemcount=itemcount;
        this.status=status;
        this.payments=payment;
        this.time=time;*/
     //   this.orderid=order;
    }

    public String getqty() {
        return qty;
    }

    public String getname() {
        return name;
    }
    public String getorder() {
        return orderid;
    }
    public String getusername() {
        return username;
    }
    public String getStatus() {
        return status;
    }
    public String getphone() {
        return phone;
    }
    public String gettotalprice() {
        return totalprice;
    }
    public int getitemcount() {
        return itemcount;
    }
    public String getime() {
        return time;
    }







}
