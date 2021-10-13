package com.ah.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ah.data.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

}
