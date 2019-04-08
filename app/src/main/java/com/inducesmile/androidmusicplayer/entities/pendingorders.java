package com.inducesmile.androidmusicplayer.entities;

public class pendingorders {

    private String payments;
    private String totalprice;
    private String time;
    private Integer itemcount;
    private  String orderid;
    private String phone;
    private String username;
    private  String status;

    public pendingorders(String orderid, String Username, String time, Integer itemcount, String totalprice, String phone, String Payment,String status) {
        this.payments = Payment;
        this.time = time;
        this.orderid = orderid;
        this.totalprice=totalprice;
        this.phone=phone;
        this.username=Username;
        this.itemcount=itemcount;
        this.status=status;
    }

    public String getime() {
        return time;
    }

    public String getpaymentmode() {
        return payments;
    }

    public String gettotalprice(){return  totalprice;}

    public String getusername(){return username;}

    public int getitemcounts() {
        return itemcount;
    }

    public String getStatus(){return  status;}

    public String getphone() {
        return phone;
    }

    public String getorderid() {
        return orderid;
    }
}
