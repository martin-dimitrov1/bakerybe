package com.example.bakery.repositories;

import com.example.bakery.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT o.billingAddress FROM Order o WHERE o.id = :orderId")
    Optional<Address> findByOrderId(Long orderId);
}
