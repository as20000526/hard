package com.example.hard;

public class LoginModel {
    private String so_id;
    private String customer_name;
    private String mo_id;
    public String item_id;
    public String online_date;
    public int qty;
    public LoginModel(String so_id,String customer_name,String mo_id,String online_date,int qty,String item_id ) {
        this.so_id=so_id;
        this.customer_name=customer_name;
        this.mo_id=mo_id;


        this.item_id=item_id;
        this.online_date=online_date;
        this.qty=qty;
    }
    public  String  getSo_id(){return so_id;}
    public String getcustomer_name(){return customer_name;}
    public String getmo_id(){return mo_id;}


    public String getitem_id(){return item_id;}
    public String getonline_date(){return online_date;}
    public int getqty(){return qty;}
}
