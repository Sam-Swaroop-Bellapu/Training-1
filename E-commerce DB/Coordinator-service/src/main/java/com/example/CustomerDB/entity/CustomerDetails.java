package com.example.CustomerDB.entity;
import javax.persistence.*;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {


    @Column
    private String firstName;

    @Column
    private String lastName;

    @Id
    @Column
    private String email;

    @Column
    private Integer mobileNumber;

    @Column
    private String myPassword;


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
