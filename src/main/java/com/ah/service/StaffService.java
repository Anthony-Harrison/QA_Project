package com.ah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ah.data.Staff;
import com.ah.dto.StaffWithCinemaDTO;
import com.ah.exception.StaffNotFoundException;
import com.ah.repo.StaffRepo;

@Service
public class StaffService {
	private StaffRepo repo;
	private ModelMapper mapper;

	public StaffService(StaffRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public StaffWithCinemaDTO mapDTO(Staff staff) {
		StaffWithCinemaDTO dto = new StaffWithCinemaDTO();

		dto.setName(staff.getName());
		dto.setId(staff.getId());
		dto.setBranch(staff.getCinema().getBranch());
		dto.setNoOfScreens(staff.getCinema().getNoOfScreens());

		return dto;
	}

	public StaffWithCinemaDTO getStaffById(Integer id) {
		return this.mapper.map(repo.findById(id).orElseThrow(StaffNotFoundException::new), StaffWithCinemaDTO.class);
	}

	public List<StaffWithCinemaDTO> getAllStaff() {
		List<Staff> saved = this.repo.findAll();
		List<StaffWithCinemaDTO> send = new ArrayList<>();
		for (Staff s : saved) {
			send.add(mapDTO(s));
		}
		return send;
	}

	public Staff createStaff(Staff staff) {
		return this.repo.save(staff);
	}

	public Staff updateStaff(Staff staff, Integer id) {
		Optional<Staff> optionalStaff = this.repo.findById(id);
		Staff toUpdate = optionalStaff.orElseThrow(StaffNotFoundException::new);
		toUpdate.setName(staff.getName());
		toUpdate.setCinema(staff.getCinema());

		return this.repo.save(toUpdate);
	}

	public boolean deleteStaff(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}