package com.Employee.employeeDBWithJPA.controller;



import com.Employee.employeeDBWithJPA.entity.EmployeeDetails;
import com.Employee.employeeDBWithJPA.model.*;
import com.Employee.employeeDBWithJPA.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping("/All-Employees-Data")
//    List<EmployeeModel> InitData() {
//
//
//        return (List<EmployeeModel>) employeeService.getEmployeeDetails();
//    }
//
//    @GetMapping("/Employees-By-City/{city}")
//    public List EmployeesByCity(@PathVariable String city){
//         return  employeeService.getEmployeeByCity(city);
//    }

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    public EmployeeDetails add(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/get/{id}")
    public Employee get(@PathVariable long id){
        return employeeService.getEmployeeById(id);

    }

    @DeleteMapping("/delete-employee/{id}")
    public void delete(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
    }





//    @PostMapping("/add-employee")
//    List<EmployeeModel> add(@RequestBody EmployeeModel newEmployee){
//        return employeeService.add(newEmployee);
//    }
//
//    @PostMapping("/add-benefits")
//    List<BenefitsModel> add(@RequestBody BenefitsModel newBenefits){
//        return employeeService.addBenefit(newBenefits);
//    }
//
//    @PostMapping("/add-location")
//    List<LocationModel> add(@RequestBody LocationModel newLocation){return employeeService.addLocation(newLocation);}
//
//    @PostMapping("/add-department")
//    List<DepartmentModel> add(@RequestBody DepartmentModel newDepartment){return employeeService.addDepartment(newDepartment);}

//    @GetMapping("/get-employees")

//    {
//        "employeeDetails":{
//             "name": "Sam",
//                "salary":2000,
//
//
//
//
//        },
//       "departmentDetails": {
//              "name":"Health"
//        }
//    }





}
