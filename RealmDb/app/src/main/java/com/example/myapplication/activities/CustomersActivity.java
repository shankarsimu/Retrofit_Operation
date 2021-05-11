package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiCallInterface;
import com.example.myapplication.PostDataActivity;
import com.example.myapplication.ProductsPostActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomersAdapter;
import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.CustomerData;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CustomerData> customersList = new ArrayList<>();
    CustomersAdapter customersAdapter;
    Button btn_click, btn_move;
    FloatingActionButton mAddAlarmFab, mAddPersonFab, mAddProductDetails;
    ExtendedFloatingActionButton mAddFab;
    TextView addAlarmActionText, addPersonActionText, addProductDetailsText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        recyclerView = findViewById(R.id.list_item);
//        btn_click = findViewById(R.id.btn_invoice);
//        btn_move = findViewById(R.id.btn_product);
//        btn_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CustomersActivity.this, InvoicesActivity.class);
//                startActivity(intent);
//            }
//        });
//        btn_move.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CustomersActivity.this, ProductsActivity.class);
//                startActivity(intent);
//            }
//        });
        showData();
//-----------------------------------------------------------
        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);
        mAddProductDetails = findViewById(R.id.add_product);
        addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        addPersonActionText = findViewById(R.id.add_person_action_text);
        addProductDetailsText = findViewById(R.id.add_product_details);
        mAddAlarmFab.setVisibility(View.GONE);
        mAddPersonFab.setVisibility(View.GONE);
        mAddProductDetails.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);
        addProductDetailsText.setVisibility(View.GONE);
        isAllFabsVisible = false;
        mAddFab.shrink();
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            mAddAlarmFab.show();
                            mAddPersonFab.show();
                            mAddProductDetails.show();
                            addAlarmActionText.setVisibility(View.VISIBLE);
                            addPersonActionText.setVisibility(View.VISIBLE);
                            addProductDetailsText.setVisibility(View.VISIBLE);
                            mAddFab.extend();
                            isAllFabsVisible = true;
                        } else {
                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            mAddProductDetails.hide();
                            addAlarmActionText.setVisibility(View.GONE);
                            addPersonActionText.setVisibility(View.GONE);
                            addProductDetailsText.setVisibility(View.GONE);
                            mAddFab.shrink();
                            isAllFabsVisible = false;
                        }
                    }
                });
        mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CustomersActivity.this, InvoicesActivity.class);
                        startActivity(intent);
                    }
                });
        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CustomersActivity.this, PostDataActivity.class);
                        startActivity(intent);
                    }
                });
        mAddProductDetails.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CustomersActivity.this, ProductDetailsActivity.class);
                        startActivity(intent);
                    }
                });
//-----------------------------------------------------------

    }

    private void showData() {
        // customersList = new ArrayList<>();
        String bearerToken = getSharedPreferences("myapplication_preference", MODE_PRIVATE)
                .getString("token", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface apiCustomers = retrofit.create(ApiCallInterface.class);

        Call<CommonResponse> call = apiCustomers.getCustomersData("Bearer " + bearerToken);
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
                    customersList.clear();
                    customersList.addAll(response.body().getData().getCustomers());
                    Log.d("mukesh", "" + customersList.size());
                    customersAdapter = new CustomersAdapter(getApplicationContext(), customersList);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    //(CustomersActivity.this,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(customersAdapter);

                }


            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed....", Toast.LENGTH_LONG).show();

            }
        });
    }
}