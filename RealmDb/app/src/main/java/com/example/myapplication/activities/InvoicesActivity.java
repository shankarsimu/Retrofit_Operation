package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.ApiCallInterface;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomersAdapter;
import com.example.myapplication.adapter.InvoicesListAdapter;
import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.Invoices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvoicesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InvoicesListAdapter invoicesListAdapter;
    List<Invoices> invoicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);
        recyclerView = findViewById(R.id.inc_rv_list);
        invoicesList = new ArrayList<>();

        invoicesData();
    }

    private void invoicesData() {
        // customersList = new ArrayList<>();
        String bearerToken = getSharedPreferences("myapplication_preference", MODE_PRIVATE)
                .getString("token", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface apiCustomers = retrofit.create(ApiCallInterface.class);

        Call<CommonResponse> call = apiCustomers.getInvoicesData("Bearer "+bearerToken);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                Log.d("sns", "" + response.code());
//
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
//
                    invoicesList.addAll(response.body().getData().getInvoices());

                    Log.d("sns", "" + invoicesList.size());
                    invoicesListAdapter = new InvoicesListAdapter(getApplicationContext(), invoicesList);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    //(CustomersActivity.this,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(invoicesListAdapter);

                }


            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed....", Toast.LENGTH_LONG).show();

            }
        });
    }
}