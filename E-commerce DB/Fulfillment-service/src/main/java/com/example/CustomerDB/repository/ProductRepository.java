package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, String> {
    ProductDetails findByProductCode(String s);
}
