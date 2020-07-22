package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_activity extends AppCompatActivity implements View.OnClickListener {
    private Button online_datebt,online_number,online_customer,manufacturebt,checked;
    private EditText data,ednumber,edcustomer,mo_idte;
    private ApiService apiService;
    private Spinner spingarr;
    private String token,org_id,so_id="",customer_name="",mo_id="",routing_level="";
    private String customer,sale_order,manufacture,online_date;
    List<LoginModel> number =new ArrayList<>();
    List<LoginModel> on_customer =new ArrayList<>();
    List<LoginModel> facture =new ArrayList<>();
    private RecyclerView recyclerView_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        recyclerView_list=findViewById(R.id.recyclerView);
        spingarr=findViewById(R.id.made_spinner);
        online_datebt=findViewById(R.id.online_date);
        online_number=findViewById(R.id.online_number);
        online_customer=findViewById(R.id.online_customer);
        manufacturebt=findViewById(R.id.manufacture);
        checked=findViewById(R.id.checked);
        ednumber=findViewById(R.id.editText2);
        edcustomer=findViewById(R.id.editText3);
        mo_idte=findViewById(R.id.mo_id);
        data =  findViewById(R.id.data);

        online_datebt.setOnClickListener(this);
        online_number.setOnClickListener(this);
        online_customer.setOnClickListener(this);
        manufacturebt.setOnClickListener(this);
        checked.setOnClickListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
                .baseUrl("http://10.1.1.71:8000/api/")
                .build();
        apiService= retrofit.create(ApiService.class);
        SharedPreferences sharedPreferences= getSharedPreferences("data", Context .MODE_PRIVATE);
        token=sharedPreferences.getString("token","");
        org_id=sharedPreferences.getString("org_id","");

        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.spingarr, android.R.layout.simple_spinner_item );
        spingarr.setAdapter(nAdapter);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spingarr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                routing_level="";
                if(i==1){
                    routing_level="11";
                }
                else if(i==2){
                    routing_level="12";}
                else if(i==3){
                    routing_level="13";}
                else if(i==4){
                    routing_level="20";}
                else if(i==5){
                    routing_level="30";}
                else if(i==6){
                    routing_level="40";}
                else if(i==7){
                    routing_level="50";}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
public void onClick(View view) {
        switch(view.getId()){
            case R.id.online_date:
                gotodata();
                break;
            case R.id.online_number:
                gotonumber();
                break;
            case R.id.online_customer:
                gotocustomer();
                break;
            case  R.id.manufacture:
                gotomo_id();
                break;
            case R.id.checked:
                gotonext();
                break;
        }


    }


private void gotodata(){
                    Calendar c = Calendar.getInstance();
        new DatePickerDialog(Search_activity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
               data.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
               online_date=year+"/"+(monthOfYear+1)+"/"+dayOfMonth;
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

        }

private void gotonumber(){
           apiService.gettoken(so_id,token).enqueue(new Callback<List<LoginModel>>() {
               @Override
               public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                   List<String> test=new ArrayList<>();
                   number.clear();
                   for (LoginModel zzz:response.body()){

                       number.add(zzz);
                       test.add(zzz.getSo_id());
                   }

                   recyclerView_list.setItems(test.toArray(new String[number.size()]), new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           ednumber.setText(number.get(i).getSo_id());
                           sale_order=number.get(i).getSo_id();
                       }
                   });


                   test.clear();

               }


               @Override
               public void onFailure(Call<List<LoginModel>> call, Throwable t) {

               }

        });



}

private void gotocustomer(){
        apiService.getcustomer(customer_name,token).enqueue(new Callback<List<LoginModel>>() {

            @Override
            public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                List<String> test=new ArrayList<>();
                on_customer.clear();
                for (LoginModel zzz:response.body()){
                    on_customer.add(zzz);
                    test.add(zzz.getcustomer_name());
                }
                    dialog_list.setItems(test.toArray(new String[on_customer.size()]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edcustomer.setText(on_customer.get(i).getcustomer_name());
                            customer=on_customer.get(i).getcustomer_name();
//                        Toast.makeText(Search_activity.this, "你選的是" + number.get(i).getSo_id(), Toast.LENGTH_SHORT).show();
                        }
                    });


                dialog_list.create().show();
//                test.clear();
                }

            @Override
            public void onFailure(Call<List<LoginModel>> call, Throwable t) {

            }
        });

}

private void gotomo_id(){
    apiService.getmo(mo_id,token).enqueue(new Callback<List<LoginModel>>() {
            @Override
            public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                List<String> test=new ArrayList<>();
                facture.clear();
                for (LoginModel zzz:response.body()){
                    facture.add(zzz);
                    test.add(zzz.getmo_id());
                }
                dialog_list.setItems(test.toArray(new String[facture.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mo_idte.setText(facture.get(i).getmo_id());
                        manufacture=facture.get(i).getmo_id();
//                        Toast.makeText(Search_activity.this, "你選的是" + number.get(i).getSo_id(), Toast.LENGTH_SHORT).show();
                    }
                });
                dialog_list.show();
            }

            @Override
            public void onFailure(Call<List<LoginModel>> call, Throwable t) {
                Log.d("fail123", "onFailure: "+t);
            }
        });



}

private void gotonext(){

    Intent menu=new Intent(Search_activity.this,Menu_search.class);
    Bundle bundle = new Bundle();
    bundle.putString("manufacture",manufacture);
    bundle.putString("customer",customer);
    bundle.putString("online_date",online_date);
    bundle.putString("sale_order",sale_order);
    bundle.putString("token",token);
    bundle.putString("routing_level",routing_level);
    bundle.putString("org_id",org_id);
    menu.putExtras(bundle);
    startActivity(menu);
}
}
