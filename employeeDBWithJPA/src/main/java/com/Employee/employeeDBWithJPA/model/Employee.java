package com.Employee.employeeDBWithJPA.model;

public class Employee {
    private String employeeName;
    private float salary;

    private DepartmentModel departmentModel;
    private LocationModel locationModel;

    private BenefitsModel benefitsModel;

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

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public BenefitsModel getBenefitsModel() {
        return benefitsModel;
    }

    public void setBenefitsModel(BenefitsModel benefitsModel) {
        this.benefitsModel = benefitsModel;
    }




    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }
}
