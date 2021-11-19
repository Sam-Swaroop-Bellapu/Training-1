package com.example.CustomerDB.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shipping_details")
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    private String productCode;

    @Column
    private String type;

    @Column
    private String line1;

    @Column
    private String line2;

    @Column
    private String postalCode;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private boolean shippingAddress;

    public ShippingDetails() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(boolean shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
