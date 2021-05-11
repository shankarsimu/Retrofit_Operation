package com.example.myapplication;

public class PaymentDetails {
    private int id;
    private String description;
    private double amount;
    private String payment_mode;
    private String depositTo;
    private String referenceNo;
    private long payment_created_date;
    private long payment_updated_date;

    public PaymentDetails(int id, String description, double amount, String payment_mode, String depositTo,
                          String referenceNo, long payment_created_date, long payment_updated_date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.payment_mode = payment_mode;
        this.depositTo = depositTo;
        this.referenceNo = referenceNo;
        this.payment_created_date = payment_created_date;
        this.payment_updated_date = payment_updated_date;
    }
}
