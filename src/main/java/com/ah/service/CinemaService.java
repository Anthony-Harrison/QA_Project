package com.ah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ah.data.Cinema;
import com.ah.repo.CinemaRepo;

@Service
public class CinemaService {

	private CinemaRepo repo;

	public CinemaService(CinemaRepo repo) {
		super();
		this.repo = repo;
	}

	public Cinema getCinemaById(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Cinema> getAllCinemas() {
		return this.repo.findAll();
	}

	public Cinema createCinema(Cinema cinema) {
		return this.repo.save(cinema);
	}

	public Cinema updateCinema(Cinema cinema, Integer id) {
		Optional<Cinema> optionalCinema = this.repo.findById(id);
		Cinema toUpdate = optionalCinema.get();
		toUpdate.setBranch(cinema.getBranch());
		toUpdate.setNoOfScreens(cinema.getNoOfScreens());

		return this.repo.save(toUpdate);
	}

	public boolean deleteCinema(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}