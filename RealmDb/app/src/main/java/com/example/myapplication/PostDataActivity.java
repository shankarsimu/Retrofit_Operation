package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.ClientDetails;
import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.InvoiceDetails;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        postDataRequest();
    }

    private void postDataRequest() {
        ClientDetails clientDetails = new ClientDetails("virat furniture", "", "", "", 3023, "", 0,
                "", "", "", "Tax Id", "Business Id");
        InvoiceDetails invoiceDetails = new InvoiceDetails("Net 15", "", 0, "", "", "",
                "1620671400000", "1621967400000", "0", "",
                0.0, 0.0, "", 15);

        List<PaymentDetails> paymentDetails = new ArrayList<>();
        List<ProductList> productLists = new ArrayList<>();
        productLists.add(new ProductList(0, "Gn", 1.0, 259900.0, "",
                "FHJK", 0.0, 3, "GST", 0.0, 1, 0,
                0.0, 0, "BAG - BAGS", "BAG", "1620712079792",
                "1620712079793", "1620712079793", "1620712079793"));
        PostDataRequest postDataRequest = new PostDataRequest(clientDetails, invoiceDetails, paymentDetails, productLists,
                3722, "1620712091909", "1620712091909", 1, 0, 0, 0,
                0, 0, 0, "1620671400000", "", "1620671400000",
                1, "1620712091909", "1620712091909");


        String bearerToken = getSharedPreferences("myapplication_preference", MODE_PRIVATE)
                .getString("token", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface retrofitAPI = retrofit.create(ApiCallInterface.class);
        Call<CommonResponse> call = retrofitAPI.setPostDataRequest("Bearer " + bearerToken, postDataRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (!response.isSuccessful()) {

                    String errorMessage;
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorMessage = jObjError.getString("message");
                    } catch (Exception err) {
                        errorMessage = response.message();
                    }
                    Log.d("sns", "" + response.code());

                    Toast.makeText(getApplicationContext(), "" + errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Product added  Successfully" + response, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed....", Toast.LENGTH_LONG).show();

            }
        });


    }


}