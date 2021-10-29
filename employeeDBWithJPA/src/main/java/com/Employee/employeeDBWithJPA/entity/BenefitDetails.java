package com.Employee.employeeDBWithJPA.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class BenefitDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id")
    private long id;


    @Column
    private String benefitName;

    @Column
    private String description;

    @OneToOne(mappedBy = "benefitDetails")
//    private List<EmployeeDetails> employeeDetails;
    private EmployeeDetails employeeDetails;

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public BenefitDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return benefitName;
    }

    public void setName(String name) {
        this.benefitName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
