package com.example.myapplication;

import com.example.myapplication.model.ClientDetails;
import com.example.myapplication.model.InvoiceDetails;

import java.util.List;

public class PostDataRequest {

    private ClientDetails ClientDetails;
    private InvoiceDetails InvoiceDetails;
    private List<PaymentDetails> PaymentDetails;
    private List<ProductList> Products;
    private int company_id;
    private String created_at;
    private String created_date;
    private int flag;
    private int id;
    private int is_recurring;
    private int is_recurring_enabled;
    private int is_recurring_parent;
    private int mail_status;
    private int next_recurring_date;
    private String recurring_end_date;
    private String recurring_span;

    public PostDataRequest(com.example.myapplication.model.ClientDetails clientDetails,
                           com.example.myapplication.model.InvoiceDetails invoiceDetails,
                           List<com.example.myapplication.PaymentDetails> paymentDetails, List<ProductList> products,
                           int company_id, String created_at, String created_date, int flag, int id, int is_recurring,
                           int is_recurring_enabled, int is_recurring_parent, int mail_status, int next_recurring_date,
                           String recurring_end_date, String recurring_span, String recurring_start_date, int status, String updated_at,
                           String updated_date) {
        ClientDetails = clientDetails;
        InvoiceDetails = invoiceDetails;
        PaymentDetails = paymentDetails;
        Products = products;
        this.company_id = company_id;
        this.created_at = created_at;
        this.created_date = created_date;
        this.flag = flag;
        this.id = id;
        this.is_recurring = is_recurring;
        this.is_recurring_enabled = is_recurring_enabled;
        this.is_recurring_parent = is_recurring_parent;
        this.mail_status = mail_status;
        this.next_recurring_date = next_recurring_date;
        this.recurring_end_date = recurring_end_date;
        this.recurring_span = recurring_span;
        this.recurring_start_date = recurring_start_date;
        this.status = status;
        this.updated_at = updated_at;
        this.updated_date = updated_date;
    }

    private String recurring_start_date;
    private int status;
    private String updated_at;
    private String updated_date;


}
