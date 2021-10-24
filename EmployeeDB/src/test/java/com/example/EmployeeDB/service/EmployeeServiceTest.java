package com.example.EmployeeDB.service;



import com.example.EmployeeDB.EmployeeDetails;
import com.example.EmployeeDB.controller.EmployeeController;
import com.example.EmployeeDB.model.EmployeeModel;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
//    @Mock
//    EmployeeDetails empDetails;



    @InjectMocks
    private EmployeeService employeeService;




//    @Mock
//    private EmployeeService employeeService.

    @Test
    public void getEmployeeDetails() {
//        System.out.println(empDetails);
        assertEquals(3,employeeService.getEmployeeDetails().size());

    }

  @Test
    public void getEmployeeByCity() {
        List<EmployeeModel> employees = employeeService.getEmployeeByCity("Hyderabad");

        assertEquals(2,employees.size());
        assertEquals("Sam",employeeService.getEmployeeByCity("Hyderabad").get(0).getEmployeeName());
        assertEquals("Satya",employeeService.getEmployeeByCity("Hyderabad").get(1).getEmployeeName());


    }
}

