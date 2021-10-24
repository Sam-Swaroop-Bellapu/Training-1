package com.example.EmployeeDB.service;

import com.example.EmployeeDB.EmployeeDetails;
import com.example.EmployeeDB.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDetails employeeDetails = new EmployeeDetails();


    public List<EmployeeModel> getEmployeeDetails() {
        return employeeDetails.getAllEmployees();
    }


    public List<EmployeeModel> getEmployeeByCity(String city){

        return employeeDetails.getAllEmployees().stream().filter(x->x.getLocationId().getLocationName().equals(city)).collect(Collectors.toList());



    }

}
