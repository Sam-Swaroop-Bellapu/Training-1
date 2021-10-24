package com.example.EmployeeDB.model;

import java.util.List;

public class EmployeeModel {
    private String employeeId;
    private String employeeName;
    private float salary;
//    private String locationId;
    private Location locationId;
//    private String deptId;
    private List<Department> deptId;
//    private String benefitId;
    private List<Benefits> benefitId;

    public EmployeeModel(String employeeId, String employeeName, float salary, Location locationId, List<Department> deptId, List<Benefits> benefitId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.locationId = locationId;
        this.deptId = deptId;
        this.benefitId = benefitId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }



    public List<Department> getDeptId() {
        return deptId;
    }

    public void setDeptId(List<Department> deptId) {
        this.deptId = deptId;
    }

    public List<Benefits> getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(List<Benefits> benefitId) {
        this.benefitId = benefitId;
    }
}
