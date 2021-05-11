package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class Invoices {

    @SerializedName("id")
    private int id;

    @SerializedName("ClientDetails")
    private ClientDetails clientDetails;

    @SerializedName("InvoiceDetails")
    private InvoiceDetails invoiceDetails;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public InvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
