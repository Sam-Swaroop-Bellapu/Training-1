package com.example.CustomerDB.model;

public class CustomerModel {
    private String firstName;
    private String lastName;
    private String email;
    private Integer mobileNumber;
    private String myPassword;

    public CustomerModel(String firstName, String lastName, String email, Integer mobileNumber, String myPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.myPassword = myPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMyPassword() {
        return myPassword;
    }

    public void setMyPassword(String myPassword) {
        this.myPassword = myPassword;
    }
}
