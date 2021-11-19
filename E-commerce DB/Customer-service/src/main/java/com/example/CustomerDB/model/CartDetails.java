package com.example.CustomerDB.model;

public class CartDetails {
    private String email;
    private String productCode;
    private String skuCode;
    private String quantityAvailable;

    public CartDetails() {
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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
