package com.example.demo.model;

import java.util.Optional;

public class EmployeeModel {
    private int id;
    private String name;
    private String designation;
    private float salary;

    public float getSalary() {
        return salary;
    }

    public float setSalary(float salary) {
        this.salary = salary;
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Optional<EmployeeModel> setName(String name) {
        this.name = name;
        return null;
    }

    public String getDesignation() {
        return designation;
    }

    public Optional<EmployeeModel> setDesignation(String designation) {
        this.designation = designation;
        return null;
    }
}
