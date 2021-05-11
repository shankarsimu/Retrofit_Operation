package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("user")
    private APIData user;

    @SerializedName("token")
    private String token;

    @SerializedName("customers")
    private List<CustomerData> customers;

    @SerializedName("invoices")
    private List<Invoices> invoices;

    @SerializedName("products")
    private List<Products> products;

    public String getToken() {
        return token;
    }

    public APIData getUser() {
        return user;
    }

    public List<CustomerData> getCustomers() {
        return customers;
    }

    public List<Invoices> getInvoices() {
        return invoices;
    }

    public List<Products> getProducts() {
        return products;
    }
}
