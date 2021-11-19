package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.CartDetails;
import com.example.CustomerDB.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartDetails, String> {
    List<CartDetails> findByEmail(String email);
}
