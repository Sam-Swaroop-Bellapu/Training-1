package com.example.CustomerDB.repository;


import com.example.CustomerDB.entity.SKUPriceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKUPriceRepository extends JpaRepository<SKUPriceDetails, String> {
}
