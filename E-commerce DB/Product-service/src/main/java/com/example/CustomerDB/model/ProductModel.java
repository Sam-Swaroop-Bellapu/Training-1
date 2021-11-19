package com.example.CustomerDB.model;

public class ProductModel {
    String email;
    String productCode;
    String productName;
    String description;


    public ProductModel(String email, String productCode, String productName, String description) {
        this.email = email;
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
