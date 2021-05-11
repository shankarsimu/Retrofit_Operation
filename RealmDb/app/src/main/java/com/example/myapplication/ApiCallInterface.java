package com.example.myapplication;


import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.Data;
import com.example.myapplication.model.DataModel;
import com.example.myapplication.model.ProductRequest;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiCallInterface {
    @GET("/api/login")
    Call<List<Data>> getData();

    @GET("posts/1/comments")
    Call<List<DataModel>> getDataList();


    @POST("/api/login")
    Call<CommonResponse> createPost(@Body HashMap<String, String> body);

    @Headers("Accept: application/json")
    @POST("/api/customers/list")
    Call<CommonResponse> getCustomersData(@Header("Authorization") String bearerToken);

    @Headers("Accept: application/json")
    @POST("/api/invoices/list")
    Call<CommonResponse> getInvoicesData(@Header("Authorization") String bearerToken);

    @Headers("Accept: application/json")
    @POST("/api/products/list")
    Call<CommonResponse> getProductsList(@Header("Authorization") String bearerToken);

    @Headers("Accept: application/json")
    @POST("/api/products")
    Call<CommonResponse> createProduct(
            @Header("Authorization") String bearerToken, @Body ProductRequest productRequest);

    @Headers("Accept: application/json")
    @POST("/api/invoices")
    Call<CommonResponse> setPostDataRequest(
            @Header("Authorization") String bearerToken, @Body PostDataRequest postDataRequest);

}
