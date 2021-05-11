package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceDetails {
    @SerializedName("terms")
    private String terms;
    private String balance_due;
    private int bank_details_added;
    private String currency_code;
    private String currency_symbol;
    private String id;
    private String invoice_date;
    private String invoice_due_date;
    private String invoice_number;
    private String note;
    private double overall_discount;
    private double tds_amount;
    private String tds_type;
    private int term_value;

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public InvoiceDetails(String terms, String balance_due, int bank_details_added, String currency_code,
                          String currency_symbol, String id, String invoice_date, String invoice_due_date, String invoice_number,
                          String note, double overall_discount, double tds_amount, String tds_type, int term_value) {
        this.terms = terms;
        this.balance_due = balance_due;
        this.bank_details_added = bank_details_added;
        this.currency_code = currency_code;
        this.currency_symbol = currency_symbol;
        this.id = id;
        this.invoice_date = invoice_date;
        this.invoice_due_date = invoice_due_date;
        this.invoice_number = invoice_number;
        this.note = note;
        this.overall_discount = overall_discount;
        this.tds_amount = tds_amount;
        this.tds_type = tds_type;
        this.term_value = term_value;
    }
}
