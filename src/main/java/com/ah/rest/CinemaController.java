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

import com.ah.data.Cinema;
import com.ah.dto.CinemaDTO;
import com.ah.service.CinemaService;

@RestController
public class CinemaController {

	private CinemaService service;

	public CinemaController(CinemaService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createCinema")
	public ResponseEntity<Cinema> createCinema(@RequestBody Cinema cinema) {
		Cinema responseBody = this.service.createCinema(cinema);
		return new ResponseEntity<Cinema>(responseBody, HttpStatus.CREATED);

	}

	@PutMapping("/updateCinema/{id}")
	public ResponseEntity<Cinema> updateCinema(@RequestBody Cinema cinema, @PathVariable Integer id) {
		Cinema responseBody = this.service.updateCinema(cinema, id);
		return new ResponseEntity<Cinema>(responseBody, HttpStatus.ACCEPTED);
	}

	@GetMapping("getCinemaById/{id}")
	public CinemaDTO getCinemaById(@PathVariable Integer id) {
		return this.service.getCinemaById(id);
	}

	@GetMapping("/getAllCinemas")
	public List<CinemaDTO> getAllCinemas() {
		return this.service.getAllCinemas();
	}

	@DeleteMapping("/removeCinema/{id}") // 204 - No content
	public ResponseEntity<?> deleteCinema(@PathVariable Integer id) {
		boolean deleted = this.service.deleteCinema(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
