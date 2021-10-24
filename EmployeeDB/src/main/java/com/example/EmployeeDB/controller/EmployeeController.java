package com.example.EmployeeDB.controller;


import com.example.EmployeeDB.EmployeeDetails;
import com.example.EmployeeDB.model.EmployeeModel;
import com.example.EmployeeDB.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/All-Employees-Data")
    List<EmployeeModel> InitData() {


        return (List<EmployeeModel>) employeeService.getEmployeeDetails();
    }

    @GetMapping("/Employees-By-City/{city}")
    public List EmployeesByCity(@PathVariable String city){
         return  employeeService.getEmployeeByCity(city);
    }


}
