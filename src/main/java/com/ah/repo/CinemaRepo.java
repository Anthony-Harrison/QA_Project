package com.ah.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ah.data.Cinema;

public interface CinemaRepo extends JpaRepository<Cinema, Integer> {

}
