package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText account, password;
    private Button button;
    ApiService apiService;
    private String user,pw,token,org_id;
    private int status=1;
    Observable  observable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.login);
        account = findViewById(R.id.user);
        password = findViewById(R.id.password);
        button.setOnClickListener(this);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
                .baseUrl("http://10.1.1.71:8000/api/")
                .build();
        apiService = retrofit.create(ApiService.class);

//        //  1. 创建被观察者 Observable 对象
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            // 2. 在复写的subscribe（）里定义需要发送的事件
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                // 通过 ObservableEmitter类对象产生事件并通知观察者
//                // ObservableEmitter类介绍
//                // a. 定义：事件发射器
//                // b. 作用：定义需要发送的事件 & 向观察者发送事件
//                emitter.onNext(user);
//                emitter.onNext(pw);
//                emitter.onComplete();
//            }
//        });

    }

    @Override

    public void onClick(View view) {
        user=account.getText().toString();
        pw=password.getText().toString();

        if (user.equals("") && pw.equals("")) {
            Toast.makeText(getApplicationContext(), "請輸入數值", Toast.LENGTH_SHORT).show();
        }
        else {
            apiService.getLoginToken(user,pw).enqueue(new Callback<LoginTokenModel>() {
                @Override
                public void onResponse(Call<LoginTokenModel> call, Response<LoginTokenModel> response) {
                    //token=response.body().getToken();
                    try{
                    token=response.body().getToken();
                    org_id=response.body().getorg_id();
                     SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.putString("org_id",org_id);
                        editor.commit();
                    status=response.body().getStatus();}
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "帳密錯誤", Toast.LENGTH_SHORT).show();
                    }
                    if(status==0){
                        status=1;
                    Intent login=new Intent(MainActivity.this,Main.class);
                    startActivity(login);
                        Log.d("123", "onResponse: "+token); }
                }
//                    Observer<String> observer = new Observer<String>() {
//
//                        // 通过复写对应方法来 响应 被观察者
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//                        // 默认最先调用复写的 onSubscribe（）
//
//                        @Override
//                        public void onNext(String value) {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.d("dd", "onError: "+e);
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    };
//                    observable.subscribe(observer);


                @Override
                public void onFailure(Call<LoginTokenModel> call, Throwable t) {
                    Log.d("fail", "onFailure: "+t);
                }
            });

//        }
    }}

}