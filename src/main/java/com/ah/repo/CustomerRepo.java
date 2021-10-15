package com.ah.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ah.data.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
