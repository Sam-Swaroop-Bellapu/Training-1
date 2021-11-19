package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long> {
}
