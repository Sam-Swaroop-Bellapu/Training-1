package com.Employee.employeeDBWithJPA.model;

public class BenefitsModel {

    private String benefitName;
    private String description;

//    public BenefitsModel( String benefitName, String description) {
//
//        this.benefitName = benefitName;
//        this.description = description;
//
//    }



    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
