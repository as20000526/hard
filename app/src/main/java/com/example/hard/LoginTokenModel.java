package com.example.hard;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class LoginTokenModel {
    /**
     * status : 0
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMC4xLjEuNzE6ODAwMFwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU3NzMzOTMyOCwiZXhwIjoxNTc3MzQyOTI4LCJuYmYiOjE1NzczMzkzMjgsImp0aSI6Inp1bGVod3JrMFM0T2E2NWUiLCJzdWIiOjIsInBydiI6ImY5MzA3ZWI1ZjI5YzcyYTkwZGJhYWVmMGUyNmYwMjYyZWRlODZmNTUifQ.alX7Iepd6tW2XsOWUxyiWbeTqpyzqBlZr6z5e6pIsJw
     * routing_level : 50
     */

    private int status;
    private String token;
    private String routing_level;
    private String message;
//    private List so_id =new ArrayList<>();
    private  String so_id;

    public LoginTokenModel(int status, String token) {
        this.status=status;
        this.token=token;
        this.message=message;
        this.so_id=so_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return  message;
    }

    public String getRouting_level() {
        return routing_level;
    }

    public void setRouting_level(String routing_level) {
        this.routing_level = routing_level;
    }


}

