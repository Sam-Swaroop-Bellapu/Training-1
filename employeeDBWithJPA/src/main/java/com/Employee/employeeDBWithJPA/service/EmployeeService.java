package com.Employee.employeeDBWithJPA.service;

import com.Employee.employeeDBWithJPA.entity.BenefitDetails;
import com.Employee.employeeDBWithJPA.entity.DepartmentDetails;
import com.Employee.employeeDBWithJPA.entity.EmployeeDetails;
import com.Employee.employeeDBWithJPA.entity.LocationDetails;
import com.Employee.employeeDBWithJPA.model.*;

import com.Employee.employeeDBWithJPA.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDetails employeeDetails;


//    private List<EmployeeModel> employees;
//
//    private List<LocationModel> locations;
//
//    private List<BenefitsModel> benefits;
//
//    private List<DepartmentModel> departments;
//
//    public List<EmployeeModel> add(EmployeeModel employeeModel) {
//        if (employees == null) {
//            employees = new ArrayList<>();
//        }
//        employees.add(employeeModel);
//        return employees;
//    }
//
//
//
//    public List<LocationModel> addLocation(LocationModel newLocation){
//        if(locations ==null){
//            locations =new ArrayList<>();
//        }
//        locations.add(newLocation);
//        return locations;
//    }
//
//    public List<BenefitsModel> addBenefit(BenefitsModel newbenefit){
//
//        if(benefits== null){
//            benefits=new ArrayList<>();
//        }
//        benefits.add(newbenefit);
//        return benefits;
//
//    }
//
//    public List<DepartmentModel> addDepartment(DepartmentModel newDepartment){
//        if(departments ==null){
//            departments =new ArrayList<>();
//        }
//        departments.add(newDepartment);
//        return departments;
//    }



//    public CustomerDetails addCustomer(CustomerModel customer) {
//
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setName(customer.getCustomerDetails().getName());
//
//        PersonalDetails personalDetails = new PersonalDetails();
//        personalDetails.setMobile(customer.getPersonalDetails().getMobile());
//        personalDetails.setEmail(customer.getPersonalDetails().getEmail());
//        customerDetails.setPersonalDetails(personalDetails);
//
//        return customerRepository.save(customerDetails);
//    }

    public Employee getEmployeeById(long id){
        Optional<EmployeeDetails> empdetails= employeeRepository.findById(id);
        if(empdetails.isPresent()){
            return getEmployee(empdetails.get());
        }
        return null;
    }

    public void deleteEmployeeById(long id){
//        Optional<Employee> emp=employeeRepository.findById(id);
        employeeRepository.deleteById(id);

    }


//    public String getBenefitsByEmployeeId(long id){
//        Optional<EmployeeDetails> employeeDetails=employeeRepository.findById(id);
//        if(employeeDetails.isPresent()){
//            return getEmployee(employeeDetails.get().getBenefitDetails())
//        }
//
//    }

//    public Employee getEmployeeByName(long id){
//        Optional<EmployeeDetails> empdetails= employeeRepository.fin ;
//        if(empdetails.isPresent()){
//            return getEmployee(empdetails.get());
//        }
//        return null;
//    }

    public EmployeeDetails addEmployee(Employee employee){


        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.setEmployeeName(employee.getEmployeeName());
        employeeDetails.setSalary(employee.getSalary());

        DepartmentDetails departmentDetails=new DepartmentDetails();
        departmentDetails.setName(employee.getDepartmentModel().getDeptName());

        LocationDetails locationDetails=new LocationDetails();
        locationDetails.setName(employee.getLocationModel().getLocationName());
        locationDetails.setCountry(employee.getLocationModel().getLocationCountry());

        BenefitDetails benefitDetails=new BenefitDetails();
        benefitDetails.setName(employee.getBenefitsModel().getBenefitName());
        benefitDetails.setDescription(employee.getBenefitsModel().getDescription());


        employeeDetails.setDepartmentDetails(departmentDetails);
        employeeDetails.setLocationDetails(locationDetails);
        employeeDetails.setBenefitDetails(benefitDetails);

        return employeeRepository.save(employeeDetails);
    }

    public Employee getEmployee(EmployeeDetails employeeDetails){
        Employee employee=new Employee();
        LocationModel locationModel=new LocationModel();
        DepartmentModel departmentModel=new DepartmentModel();
        BenefitsModel benefitsModel=new BenefitsModel();

        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setSalary(employeeDetails.getSalary());

        locationModel.setLocationName(employeeDetails.getLocationDetails().getName());
        locationModel.setLocationCountry(employeeDetails.getLocationDetails().getCountry());
        employee.setLocationModel(locationModel);

        departmentModel.setDeptName(employeeDetails.getDepartmentDetails().getName());
        employee.setDepartmentModel(departmentModel);

        benefitsModel.setBenefitName(employeeDetails.getBenefitDetails().getBenefitName());
        benefitsModel.setDescription(employeeDetails.getBenefitDetails().getDescription());
        employee.setBenefitsModel(benefitsModel);

        return employee;

    }





}
