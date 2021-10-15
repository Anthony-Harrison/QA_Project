package com.ah.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ah.data.Movies;
import com.ah.service.MoviesService;

@RestController
public class MoviesController {

	private MoviesService service;

	public MoviesController(MoviesService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createMovie")
	public ResponseEntity<Movies> createMovies(@RequestBody Movies movies) {
		Movies responseBody = this.service.createMovies(movies);
		ResponseEntity<Movies> response = new ResponseEntity<Movies>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateMovies/{id}")
	public ResponseEntity<Movies> updateMovies(@RequestBody Movies movies, @PathVariable Integer id) {
		Movies responseBody = this.service.updateMovies(movies, id);
		return new ResponseEntity<Movies>(responseBody, HttpStatus.ACCEPTED);
	}

	@GetMapping("getMoviesById/{id}")
	public Movies getMoviesById(@PathVariable Integer id) {
		return this.service.getMoviesById(id);
	}

	@GetMapping("/getAllMovies")
	public List<Movies> getAllMoviess() {
		return this.service.getAllMoviess();
	}

	@DeleteMapping("/removeMovies/{id}") // 204 - No content
	public ResponseEntity<?> deleteMovies(@PathVariable Integer id) {
		boolean deleted = this.service.deleteMovies(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}