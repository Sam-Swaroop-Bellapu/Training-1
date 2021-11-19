package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.InventoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<InventoryDetails, String> {
    InventoryDetails findBySkuCode(String skuCode);
}
