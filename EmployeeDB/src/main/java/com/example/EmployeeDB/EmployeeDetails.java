package com.example.EmployeeDB;


import com.example.EmployeeDB.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeDetails {


//        List location=new ArrayList();
//        List locationList=new ArrayList();
        Location loc1=new Location("L1","Hyderabad","India");
        Location loc2=new Location("L2","Chennai","India");



//        List department=new ArrayList();
//        List departmentList=new ArrayList();
        Department dept1=new Department("DP1","HR");
        Department dept2=new Department("DP2","IT");

//        department.add(dept1);
//        departmentList.add(dept2);

    List<Department> department=Arrays.asList(dept1);
    List<Department> departmentList=Arrays.asList(dept2);





        Benefits bnft=new Benefits("B1","Medical and Transport","Medical Insurance and Transport");
        Benefits bnft1=new Benefits("B2","Leaves and Accessories","Optional leaves and Laptop Accessories");

//        List benefitList=new ArrayList();
//        benefitList.add(bnft);



//        List benefitList1=new ArrayList();
//        benefitList1.add(bnft);
//        benefitList1.add(bnft1);
        List<Benefits> benefitList=Arrays.asList(bnft);
        List<Benefits> benefitList1=Arrays.asList(bnft1);




        EmployeeModel emp1=new EmployeeModel("101","Sam",20000,loc1,department,benefitList);
        EmployeeModel emp2=new EmployeeModel("102","Swaroop",40000,loc2,departmentList,null);
        EmployeeModel emp3=new EmployeeModel("103","Satya",80000,loc1,department,benefitList1);
        List<EmployeeModel>employee= Arrays.asList(emp1,emp2,emp3);



        public List<EmployeeModel> getAllEmployees(){return employee;}











}
