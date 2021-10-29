package com.Employee.employeeDBWithJPA.repository;

import com.Employee.employeeDBWithJPA.entity.DepartmentDetails;
import com.Employee.employeeDBWithJPA.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {

//    public List<EmployeeDetails> findByEmployeeId(long employeeId);
}
