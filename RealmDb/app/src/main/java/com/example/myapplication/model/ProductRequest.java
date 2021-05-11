package com.example.myapplication.model;

public class ProductRequest {
    private String product_type;
    private String product_name;
    private String sku;
    private String hsn_code;
    private String unit;
    private String unit_value;
    private double cost;
    private int company_id;

    public ProductRequest(String product_type, String product_name, String sku, String hsn_code, String unit, String unit_value, double cost, int company_id) {
        this.product_type = product_type;
        this.product_name = product_name;
        this.sku = sku;
        this.hsn_code = hsn_code;
        this.unit = unit;
        this.unit_value = unit_value;
        this.cost = cost;
        this.company_id = company_id;
    }


}
