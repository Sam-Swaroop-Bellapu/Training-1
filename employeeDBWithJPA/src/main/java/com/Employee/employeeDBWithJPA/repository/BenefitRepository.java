package com.Employee.employeeDBWithJPA.repository;

import com.Employee.employeeDBWithJPA.entity.BenefitDetails;
import com.Employee.employeeDBWithJPA.entity.DepartmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<BenefitDetails, Long> {
}
