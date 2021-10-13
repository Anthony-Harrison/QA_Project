package com.ah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ah.data.Staff;
import com.ah.repo.StaffRepo;

@Service
public class StaffService {
	private StaffRepo repo;

	public StaffService(StaffRepo repo) {
		super();
		this.repo = repo;
	}

	public Staff getStaffById(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Staff> getAllStaffs() {
		return this.repo.findAll();
	}

	public Staff createStaff(Staff staff) {
		return this.repo.save(staff);
	}

	public Staff updateStaff(Staff staff, Integer id) {
		Optional<Staff> optionalStaff = this.repo.findById(id);
		Staff toUpdate = optionalStaff.get();
		toUpdate.setName(staff.getName());

		return this.repo.save(toUpdate);
	}

	public boolean deleteStaff(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}