package com.Employee.employeeDBWithJPA.entity;


import com.Employee.employeeDBWithJPA.model.DepartmentModel;
import com.Employee.employeeDBWithJPA.model.LocationModel;

import javax.persistence.*;

@Entity
@Table(name="employee_details")
public class EmployeeDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @Column(name = "salary")
    private float salary;

    @Column(name = "employeeName")
    private String employeeName;

//    @Column
//    private String locationId;
//
//    @Column
//    private String benefitId;
//
//    @Column
//    private String deptId;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "location_id")

    private LocationDetails locationDetails;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "department_id")

    private DepartmentDetails departmentDetails;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "benefit_id")

    private BenefitDetails benefitDetails;

    public LocationDetails getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
    }

    public BenefitDetails getBenefitDetails() {
        return benefitDetails;
    }

    public void setBenefitDetails(BenefitDetails benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }



    public DepartmentDetails getDepartmentDetails() {
        return departmentDetails;
    }

    public void setDepartmentDetails(DepartmentDetails departmentDetails) {
        this.departmentDetails = departmentDetails;
    }

    public EmployeeDetails(){

    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


}
