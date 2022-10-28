package com.example.customerapidemo.repository;

import com.example.customerapidemo.entity.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCustomerRepository extends JpaRepository<UserCustomer,Long> {
    Optional<UserCustomer> findByEmail(String email);
}
