package com.Employee.employeeDBWithJPA.repository;


import com.Employee.employeeDBWithJPA.entity.DepartmentDetails;
import com.Employee.employeeDBWithJPA.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDetails, Long> {
}
