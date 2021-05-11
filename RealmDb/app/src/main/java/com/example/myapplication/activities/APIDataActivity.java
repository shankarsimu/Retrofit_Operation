package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.adapter.APIDataFetchAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.RealmDBModel;

import java.util.List;

import io.realm.Realm;

public class APIDataActivity extends AppCompatActivity {
    private Realm realm;
    RecyclerView recyclerView;
    TextView responseData;

    List<RealmDBModel> realmDbData;
    private APIDataFetchAdapter apiDataFetchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        getIntent().getStringExtra("token");

        setContentView(R.layout.activity_a_p_i_data);
        recyclerView = findViewById(R.id.rv_list);
        responseData = findViewById(R.id.responseData);
        apiDataActivityShow();
    }

    private void apiDataActivityShow() {
        realmDbData = realm.where(RealmDBModel.class).findAll();


        apiDataFetchAdapter = new APIDataFetchAdapter(realmDbData, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(apiDataFetchAdapter);
    }
}