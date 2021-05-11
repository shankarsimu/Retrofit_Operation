package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RealmDBRVAdapter;
import com.example.myapplication.model.RealmDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class UserDetails extends AppCompatActivity {

    List<RealmDataModel> dataModals;

    private Realm realm;
    private RecyclerView userDetailsRV;
    private RealmDBRVAdapter realmDBRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userDetailsRV = findViewById(R.id.itemRV);
        realm = Realm.getDefaultInstance();
        dataModals = new ArrayList<>();


        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        dataModals = realm.where(RealmDataModel.class).findAll();

         realmDBRVAdapter = new RealmDBRVAdapter(dataModals, this);

         userDetailsRV.setLayoutManager(new LinearLayoutManager(this));

         userDetailsRV.setAdapter(realmDBRVAdapter);
    }
}