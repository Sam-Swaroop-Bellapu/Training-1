package com.example.demo.controller;

import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import com.example.demo.model.EmployeeModel;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller1 {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HelloService helloService;


    @PostMapping("/add")
    List<EmployeeModel> add(@RequestBody EmployeeModel newEmployee) {

        return employeeService.add(newEmployee);
    }

    @GetMapping("/get/{id}")
    EmployeeModel get(@PathVariable Integer id) {

        return employeeService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id, @RequestHeader String data) {
        employeeService.delete(id);
    }

    @PatchMapping("/patch/{id}")
    List<EmployeeModel> patch(@PathVariable Integer id, @RequestHeader String name,@RequestHeader String designation,@RequestHeader float salary) {
        return employeeService.patch(id, name,designation,salary);
    }

    @GetMapping("/GetSalary")
    @ResponseBody
    public float GetSalary(@RequestParam int id ){
        return employeeService.getSalary(id);

    }


}

/**
 * PATCH with example
 * Enhance existing employee with Salary
 * Query parameters - https://www.baeldung.com/spring-request-param
 * Explore Header params - https://www.baeldung.com/spring-rest-http-headers
 */
