package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.CustomerDetails;
import com.example.CustomerDB.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByEmail(String email);
}
