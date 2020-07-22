package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menu_search extends AppCompatActivity {
    private ApiService apiService;
    private String customer,sale_order,manufacture,online_date,token,org_id,routing_level;
    private Object Callback;
    private TextView howmuch;
    AlertDialog.Builder dialog_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_search);
        howmuch=findViewById(R.id.howmuch);
        Bundle bundle=this.getIntent().getExtras();
        customer=bundle.getString("customer");
        sale_order=bundle.getString("sale_order");
        manufacture=bundle.getString("manufacture");
        online_date=bundle.getString("online_date");
        token=bundle.getString("token");
        org_id=bundle.getString("org_id");
        routing_level=bundle.getString("routing_level");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
                .baseUrl("http://10.1.1.71:8000/api/")
                .build();
        apiService = retrofit.create(ApiService.class);

        apiService.getsearch(manufacture,customer,online_date,sale_order,sale_order,routing_level,org_id)
                .enqueue(new Callback<List<LoginModel>>() {

                    @Override
                    public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                        final List<LoginModel> menuarray=new  ArrayList<>();
                        List<String> test=new ArrayList<>();
                        menuarray.clear();
                        if(!(response.body().size()==0)){
                        for (LoginModel zzz:response.body()){
                            menuarray.add(zzz);
                            test.add(zzz.getSo_id());
                        }}
                        else{
                            Toast.makeText(getApplicationContext(),"查無資料",Toast.LENGTH_SHORT).show();
                        }

                        dialog_list.setItems(test.toArray(new String[menuarray.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        dialog_list.create().show();
                        test.clear();

                    }

                    @Override
                    public void onFailure(Call<List<LoginModel>> call, Throwable t) {

                    }
                });
    }

}