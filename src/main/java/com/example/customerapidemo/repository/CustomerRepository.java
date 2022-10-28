package com.example.customerapidemo.repository;

import com.example.customerapidemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
