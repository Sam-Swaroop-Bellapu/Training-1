package com.example.CustomerDB.repository;

import com.example.CustomerDB.entity.AddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressDetails, Long> {
    List<AddressDetails> findByEmail(String email);
    Optional<AddressDetails> findByType(String type);
}
