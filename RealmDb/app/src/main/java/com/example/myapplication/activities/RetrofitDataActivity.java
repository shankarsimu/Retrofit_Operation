package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.ApiCallInterface;
import com.example.myapplication.adapter.DataAdapter;
import com.example.myapplication.model.DataModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataActivity extends AppCompatActivity {

   private RecyclerView recyclerView;
    private List<DataModel> dataList;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_data);
      //  recyclerView = findViewById(R.id.rv_list);
        initViews();
    }
    private void initViews(){
        recyclerView=(RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showData();
    }
    private void showData(){
        dataList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface apiInterface = retrofit.create(ApiCallInterface.class);

        Call<List<DataModel>> call = apiInterface.getDataList();
        call.enqueue(new Callback<List<DataModel>>() {

            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                //   DataModel dataModel = response.body();
                //  dataList = new ArrayList<>(Arrays.asList(dataModel.getData()));
                Log.d("sns", "success");
                // ShowData(dataList);
                dataList = (List<DataModel>) response.body();
                dataAdapter = new DataAdapter(getApplicationContext(), dataList);
                recyclerView.setAdapter(dataAdapter);

            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
                Log.d("sns", "failure");
            }

        });
    }


    /*
    * dataArrayList = new ArrayList<>();
    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://www.whitehouse.gov/")
    * .addConverterFactory(GsonConverterFactory.create()).build();
    RequestInterface requestInterface=retrofit.create(RequestInterface.class);
    Call<List<Datum>> call= requestInterface.getJSON();
    call.enqueue(new Callback<List<Datum>>() {
        @Override
        public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
            dataArrayList = response.body();
            dataAdapter=new DataAdapter(getApplicationContext(),dataArrayList);
            recyclerView.setAdapter(dataAdapter);
        }

        @Override
        public void onFailure(Call<List<Datum>> call, Throwable t) {
            Log.e("Error",t.getMessage());
        }
    });
}
    * */

//    private void ShowData(List<DataModel> dataList) {
//        DataAdapter adapter = new DataAdapter(this, dataList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//
//    }
}



