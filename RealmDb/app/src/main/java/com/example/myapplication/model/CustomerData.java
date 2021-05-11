package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class CustomerData {
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("company_id")
    private int company_id;
    @SerializedName("company_name")
    private String company_name;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }
}
