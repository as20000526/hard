package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menu_search extends AppCompatActivity {
    private ApiService apiService;
    private String customer,sale_order,manufacture,online_date,token,org_id;
    private int routing_level;
    private Object Callback;
    private TextView howmuch;
    private RecyclerView recyclerView_list;
    List<LoginModel> menu =new ArrayList<>();
    private MyAdapter adapter;
    List<LoginModel> menuarray=new  ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getRetrofitData();

        setContentView(R.layout.activity_menu_search);
        recyclerView_list=findViewById(R.id.recyclerView);
        howmuch=findViewById(R.id.howmuch);


        Bundle bundle=this.getIntent().getExtras();
        customer=bundle.getString("customer");
        sale_order=bundle.getString("sale_order");
        manufacture=bundle.getString("manufacture");
        online_date=bundle.getString("online_date");
        token=bundle.getString("token");
        org_id=bundle.getString("org_id");
        routing_level=bundle.getInt("routing_level");

        recyclerView_list.setLayoutManager(new LinearLayoutManager(this));
        Log.d("345", "onCreate: "+customer);
        Log.d("345", "onCreate: "+routing_level);
        Log.d("345", "onCreate: "+org_id);
        Log.d("345", "onCreate: "+token);



    }

    private void getRetrofitData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)) // 使用 Gson 解析
                .baseUrl("http://10.1.1.71:8000/api/")
                .build();
        apiService = retrofit.create(ApiService.class);

        apiService.getsearch("","祥雲工具股份有限公司","","",token,"11","1000")
                .enqueue(new Callback<List<Test>>() {

                    @Override
                    public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                        Log.d("345", "onResponse: u.3");
//                        List<String> test=new ArrayList<>();
//                        menuarray.clear();
//                        if(!(response.body().size()==0)){
//                            for (LoginModel zzz:response.body()){
//                                menuarray.add(zzz);
//                                test.add(zzz.getSo_id());
//                            }}
//                        menu=menuarray;
//
//
//                        adapter=new MyAdapter(menu);
//                        recyclerView_list.setAdapter(adapter);
//                        else{
//                            Toast.makeText(getApplicationContext(),"查無資料",Toast.LENGTH_SHORT).show();
//                        }
//
//                        dialog_list.setItems(test.toArray(new String[menuarray.size()]), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//
//                        dialog_list.create().show();
//                        test.clear();

                    }

                    @Override
                    public void onFailure(Call<List<Test>> call, Throwable t) {
                        Log.d("345", "onFailure: "+t);
                    }
                });
    }

}