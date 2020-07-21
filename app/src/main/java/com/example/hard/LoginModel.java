package com.example.hard;

public class LoginModel {
    private String so_id;
    private String customer_name;
    private String mo_id;
    public LoginModel(String so_id,String customer_name,String mo_id) {
        this.so_id=so_id;
        this.customer_name=customer_name;
        this.mo_id=mo_id;
    }
    public  String  getSo_id(){return so_id;}
    public String getcustomer_name(){return customer_name;}
    public String getmo_id(){return mo_id;}
}
