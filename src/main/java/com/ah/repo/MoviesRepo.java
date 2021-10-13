package com.ah.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ah.data.Movies;

public interface MoviesRepo extends JpaRepository<Movies, Integer> {

}
