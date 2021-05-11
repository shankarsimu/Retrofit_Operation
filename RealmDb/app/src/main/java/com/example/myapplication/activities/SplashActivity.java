package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//                startActivity(intent);

                actUserLogin();
                //              finish();
            }
        }, 3000);

    }

    private void actUserLogin() {

        SharedPreferences sharedPreferences = getSharedPreferences("myapplication_preference", MODE_PRIVATE);

        if (sharedPreferences.getString("token", "").isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), CustomersActivity.class);
            startActivity(intent);
        }
    }

}