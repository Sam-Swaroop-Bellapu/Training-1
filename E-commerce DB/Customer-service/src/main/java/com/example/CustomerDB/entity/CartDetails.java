package com.example.CustomerDB.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_details")
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    private String productCode;

    @Column
    private String skuCode;

    @Column
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "email='" + email + '\'' +
                ", productCode='" + productCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
