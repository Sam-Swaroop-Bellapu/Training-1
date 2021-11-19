package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.SKUDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKURepository extends JpaRepository<SKUDetails, String> {
    SKUDetails findBySkuCode(String skuCode);
}
