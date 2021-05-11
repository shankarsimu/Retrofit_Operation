package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ClientDetails {

    @SerializedName("company_name")
    private String company_name;
    private String address;
    private String client_name;
    private String country;
    private int customer_id;
    private String email_id;
    private int id;
    private String phone_number;
    private String pincode;
    private String state;
    private String tax_key;
    private String vat_key;


    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }


    public ClientDetails(String company_name, String address, String client_name, String country, int customer_id, String email_id, int id, String phone_number, String pincode, String state, String tax_key, String vat_key) {
        this.company_name = company_name;
        this.address = address;
        this.client_name = client_name;
        this.country = country;
        this.customer_id = customer_id;
        this.email_id = email_id;
        this.id = id;
        this.phone_number = phone_number;
        this.pincode = pincode;
        this.state = state;
        this.tax_key = tax_key;
        this.vat_key = vat_key;
    }
}
