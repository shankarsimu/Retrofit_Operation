package com.example.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiCallInterface;
import com.example.myapplication.R;
import com.example.myapplication.model.CommonResponse;
import com.example.myapplication.model.RealmDBModel;

import org.json.JSONObject;

import java.util.HashMap;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText Email, Pwd;
    Button Login;
    TextView responseView;
    String responseString;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();

        Email = findViewById(R.id.email);
        Pwd = findViewById(R.id.password);
        Login = findViewById(R.id.login);

        responseView = findViewById(R.id.txtResponse);
        Login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         if (Email.getText().toString().isEmpty() && Pwd.getText().toString().isEmpty()) {
                                             Toast.makeText(getApplicationContext(), "Please enter both the values", Toast.LENGTH_SHORT).show();
                                             return;
                                         }

                                         postData(Email.getText().toString(), Pwd.getText().toString());
                                     }
                                 }
        );
    }


    public void postData(String email, String password) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.6.232.246/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCallInterface retrofitAPI = retrofit.create(ApiCallInterface.class);


        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);


        Intent intent = new Intent(LoginActivity.this, CustomersActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);

        // calling a method to create a post and passing our modal class.
        Call<CommonResponse> call = retrofitAPI.createPost(map);

        // on below line we are executing our method.
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
                    String token = response.body().getData().getToken();
                    String name = response.body().getData().getUser().getName();
                    int id = response.body().getData().getUser().getId();
                    String role_name = response.body().getData().getUser().getRole_name();

                    Log.d("sns", " " + token);
                    Log.d("sns", " " + name);
                    Log.d("sns", " " + id);
                    Log.d("sns", " " + role_name);

                    addDataToDatabase(name, email, role_name);

                }

                String SharedPrefFile = "myapplication_preference";
                String token = response.body().getData().getToken();
                SharedPreferences preferences = getSharedPreferences(SharedPrefFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token", token);
                editor.commit();

//                String tokenGet = preferences.getString("token", "");
//                responseView.setText(tokenGet);
                Toast.makeText(getApplicationContext(), "Data save in Shared Preferences", Toast.LENGTH_LONG).show();
                Log.d("sns", "Data save in Shared Preferences --  " + token);
                Intent intent = new Intent(LoginActivity.this, CustomersActivity.class);
                //   intent.putExtra("token", tokenGet);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed.......", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //method for realm db
    private void addDataToDatabase(String userName, String userEmail, String userRole) {


        RealmDBModel modal = new RealmDBModel();

        Number id = realm.where(RealmDBModel.class).max("id");

        long nextId;

        if (id == null) {
            nextId = 1;
        } else {
            nextId = id.intValue() + 1;
        }
        modal.setId(nextId);
        modal.setUserName(userName);
        modal.setUser_Email(userEmail);
        modal.setUser_Role(userRole);


        Toast.makeText(getApplicationContext(), "" + modal, Toast.LENGTH_LONG).show();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(modal);
            }
        });
    }

}

