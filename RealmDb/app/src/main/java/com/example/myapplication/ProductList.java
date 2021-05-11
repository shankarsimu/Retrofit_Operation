package com.example.myapplication;

public class ProductList {
    private int id;
    private String name;
    private double quantity;
    private double price;
    private String description;
    private String hsn_code;
    private double tax;
    private int tax_type;
    private String tax_label;
    private double discount;
    private int discount_type;
    private int number_of_days;
    private double per_day_price;
    private int show_date_range;
    private String unit;
    private String unit_value;
    private String product_created_date;
    private String product_updated_date;
    private String start_date;
    private String end_date;

    public ProductList(int id, String name, double quantity, double price, String description, String hsn_code,
                       double tax, int tax_type, String tax_label, double discount, int discount_type, int number_of_days,
                       double per_day_price, int show_date_range, String unit, String unit_value,
                       String product_created_date, String product_updated_date, String start_date, String end_date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.hsn_code = hsn_code;
        this.tax = tax;
        this.tax_type = tax_type;
        this.tax_label = tax_label;
        this.discount = discount;
        this.discount_type = discount_type;
        this.number_of_days = number_of_days;
        this.per_day_price = per_day_price;
        this.show_date_range = show_date_range;
        this.unit = unit;
        this.unit_value = unit_value;
        this.product_created_date = product_created_date;
        this.product_updated_date = product_updated_date;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
