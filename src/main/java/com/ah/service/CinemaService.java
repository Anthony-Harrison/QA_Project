package com.ah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.data.Cinema;
import com.ah.dto.CinemaDTO;
import com.ah.exception.StaffNotFoundException;
import com.ah.repo.CinemaRepo;

@Service
public class CinemaService {

	private CinemaRepo repo;
	private ModelMapper mapper;

	public CinemaService(CinemaRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public CinemaDTO mapDTO(Cinema cinema) {
		return this.mapper.map(cinema, CinemaDTO.class);
	}

	public CinemaDTO getCinemaById(Integer id) {
		Cinema saved = repo.findById(id).orElseThrow(StaffNotFoundException::new);
		return this.mapDTO(saved);
	}

	public List<CinemaDTO> getAllCinemas() {
		List<Cinema> saved = repo.findAll();
		List<CinemaDTO> toSend = new ArrayList<>();
		for (Cinema cinema : saved) {
			toSend.add(mapDTO(cinema));
		}
		return toSend;
	}

	public Cinema createCinema(Cinema cinema) {
		return this.repo.save(cinema);
	}

	public Cinema updateCinema(Cinema cinema, Integer id) {
		Optional<Cinema> optionalCinema = this.repo.findById(id);
		Cinema toUpdate = optionalCinema.orElseThrow(StaffNotFoundException::new);
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