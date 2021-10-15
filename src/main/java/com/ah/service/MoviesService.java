package com.ah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ah.data.Movies;
import com.ah.repo.MoviesRepo;

@Service
public class MoviesService {
	private MoviesRepo repo;

	public MoviesService(MoviesRepo repo) {
		super();
		this.repo = repo;
	}

	public Movies getMoviesById(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Movies> getAllMoviess() {
		return this.repo.findAll();
	}

	public Movies createMovies(Movies movies) {
		return this.repo.save(movies);
	}

	public Movies updateMovies(Movies movies, Integer id) {
		Optional<Movies> optionalMovies = this.repo.findById(id);
		Movies toUpdate = optionalMovies.get();
		toUpdate.setTitle(movies.getTitle());
		toUpdate.setRuntime(movies.getRuntime());

		return this.repo.save(toUpdate);
	}

	public boolean deleteMovies(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}
