package com.example.demo.service;

import com.example.demo.model.EmployeeModel;
import com.example.demo.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private List<EmployeeModel> employees;

    public List<EmployeeModel> add(EmployeeModel employeeModel) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employeeModel);
        return employees;
    }

    public EmployeeModel get(int id) {

        Optional<EmployeeModel> first = employees.stream().filter(employeeModel -> employeeModel.getId() == id).findFirst();

        return first.isPresent() ? first.get() : null;
    }

    public void delete(int id) {

        Optional<EmployeeModel> first = employees.stream().filter(employeeModel -> employeeModel.getId() == id).findFirst();

        first.ifPresent(employeeModel -> employees.remove(employeeModel));
    }

    public List<EmployeeModel> patch(int id, String Change,String Change1,float Change2){
        Optional<EmployeeModel> ChangeName=employees.stream().filter(EmployeeChange->EmployeeChange.getId()==id).findFirst().get().setName(Change);
        Optional<EmployeeModel> ChangeDesignation=employees.stream().filter(EmployeeChange->EmployeeChange.getId()==id).findFirst().get().setDesignation(Change1);
        float ChangeSalary=employees.stream().filter(EmployeeChange->EmployeeChange.getId()==id).findFirst().get().setSalary(Change2);


        return employees;


    }

    public float getSalary(int id){

        return employees.stream().filter(salary->salary.getId()==id).findFirst().get().getSalary();

    }


}