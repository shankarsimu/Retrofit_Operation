package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.ProductRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsPostActivity extends AppCompatActivity {

    private EditText edProductServiceName, edSku, edCost, edHsnCode;
    private TextInputLayout spinner_unit;
    private EditText edV;
    private AutoCompleteTextView productType, unitType;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        edProductServiceName = findViewById(R.id.edtProductServiceName);
        edSku = findViewById(R.id.edtSKU);
        edCost = findViewById(R.id.edtCost);
        edHsnCode = findViewById(R.id.edtHSN_productCode);
        spinner_unit = findViewById(R.id.unit_spinner);
        edV = findViewById(R.id.edValue);
        productType = findViewById(R.id.dropdown_text);
        unitType = findViewById(R.id.dropdown_text2);
        btn_save = findViewById(R.id.save);
        String[] items = new String[]{
                "Product/Service Type",
                "Inventory",
                "Non-Inventory",
                "Service",
                "Bundle",
                "Others"
        };

        @SuppressLint("ResourceType") ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                ProductsPostActivity.this,
                R.layout.dropdown_item,//R.array.products_type
                items
        );

        productType.setAdapter(arrayAdapter);


        String[] itemsUnit = new String[]{
                "UNIT",
                "BAG - BAGS",
                "BAL -BALE ",
                "BDL - BUNDLES",
                "BKL - BUCKLES",
                "BOU - BILLIONS OF UNITS",
                "BOX - BOX",
                "BAG - BAGS",
                "BTL - BOTTLES",
                "BUN - BUNCHES",
                "CAN - CANS",
                "CBM - CUBIC METER",
                "CTN - CARTOONS",
                "DOZ - DOZEN",
                "DRM - DRUM",
                "OTH - OTHERS"
        };

        @SuppressLint("ResourceType") ArrayAdapter<String> unitarrayAdapter = new ArrayAdapter<>(
                ProductsPostActivity.this,
                R.layout.dropdown_item2, //R.array.unit
                itemsUnit
        );
        unitType.setThreshold(1);
        unitType.setAdapter(unitarrayAdapter);
        unitType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    edV.setText("");

                } else {
                    edV.setText(parent.getItemAtPosition(position).toString());
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSku.getText().toString().isEmpty() && edHsnCode.getText().toString().isEmpty() &&
                        edCost.getText().toString().isEmpty() &&
                        edV.getText().toString().isEmpty() && edProductServiceName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postProductData(edSku.getText().toString(), edHsnCode.getText().toString(),
                        edCost.getText().toString(),
                        edV.getText().toString(), edProductServiceName.getText().toString());
            }
        });

    }

    private void postProductData(String edSku, String edHsnCode, String edCost, String edValue, String edProductServiceName) {
        String bearerToken = getSharedPreferences("myapplication_preference", MODE_PRIVATE)
                .getString("token", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductRequest productRequest = new ProductRequest(productType.getText().toString(),
                edProductServiceName,
                edSku, edHsnCode, unitType.getText().toString(), edValue,
                Double.parseDouble(edCost), 3722);


        ApiCallInterface retrofitAPI = retrofit.create(ApiCallInterface.class);
        Call<CommonResponse> call = retrofitAPI.createProduct("Bearer " + bearerToken, productRequest);
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

                    //    String finalErrorMessage = errorMessage;
                    Toast.makeText(getApplicationContext(), "" + errorMessage, Toast.LENGTH_LONG).show();
                } else {

                    Log.d("sns", " " + response.body().getMessage());

                    Toast.makeText(getApplicationContext(), "Product added  Successfully" + response, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed....", Toast.LENGTH_LONG).show();
            }
        });
    }


}

