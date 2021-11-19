package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.CustomerDetails;
import com.example.CustomerDB.entity.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingDetails, String> {
}
