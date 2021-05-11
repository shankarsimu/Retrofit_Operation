package com.example.myapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiCallInterface;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductDetailsAdapter;
import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.Products;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductDetailsAdapter productDetailsAdapter;
    List<Products> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        recyclerView = findViewById(R.id.rv_product_list);
        productDetailsData();
    }

    private void productDetailsData() {
        String bearerToken = getSharedPreferences("myapplication_preference", MODE_PRIVATE)
                .getString("token", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface apiCustomers = retrofit.create(ApiCallInterface.class);

        Call<CommonResponse> call = apiCustomers.getProductsList("Bearer " + bearerToken);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                Log.d("sns", "" + response.code());

                if (!response.isSuccessful()) {

                    String errorMessage;
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorMessage = jObjError.getString("message");

                    } catch (Exception err) {
                        errorMessage = response.message();
                    }

                    Toast.makeText(getApplicationContext(), "sns " + errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    productsList.clear();
                    productsList.addAll(response.body().getData().getProducts());

                    Log.d("sns", "" + productsList.size());
                    productDetailsAdapter = new ProductDetailsAdapter(getApplicationContext(), productsList);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    //(CustomersActivity.this,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(productDetailsAdapter);

                }


            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed....", Toast.LENGTH_LONG).show();

            }
        });
    }
}