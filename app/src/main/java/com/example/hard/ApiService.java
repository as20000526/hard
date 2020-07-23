package com.example.hard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // 會返回一個 call 類別

    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginTokenModel> getLoginToken(@Field("account") String account, @Field("password") String password);

    @GET("app-search-so")
    Call<List<LoginModel>> gettoken(@Query("so_id") String so_id, @Query("token") String token);
//    Call<LoginTokenModel> gettoken (@Field("token")String token, @Field("so_id") String so_id);
    @GET("app-search-customer")
    Call<List<LoginModel>>getcustomer(@Query("customer_name" )String customer_name,@Query("token" )String token);
    @GET("app-search-mo")
    Call<List<LoginModel>>getmo(@Query("mo_id" )String mo_id,@Query("token")String token);
    @GET("get-now-manufacture")
        Call<List<Test>>getsearch(@Query("manufacture" )String manufacture,@Query("customer")String customer,
                                    @Query("online_date" )String online_date,@Query("sale_order")String sale_order,
                                    @Query("token" )String token,@Query("routing_level") String routing_level,
                                    @Query("org_id" )String org_id);
}