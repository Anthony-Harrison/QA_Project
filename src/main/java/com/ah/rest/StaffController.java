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

import com.ah.data.Staff;
import com.ah.service.StaffService;

public class StaffController {
	private StaffService service;

	public StaffController(StaffService service) {
		super();
		this.service = service;
	}

	@GetMapping("getStaffById/{id}")
	public Staff getStaffById(@PathVariable Integer id) {
		return this.service.getStaffById(id);
	}

	@GetMapping("/getAllStaffs")
	public List<Staff> getAllStaffs() {
		return this.service.getAllStaffs();
	}

	@PostMapping("/createStaff")
	public ResponseEntity<Staff> createMarsupial(@RequestBody Staff staff) {
		Staff responseBody = this.service.createStaff(staff);
		ResponseEntity<Staff> response = new ResponseEntity<Staff>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateStaff/{id}")
	public ResponseEntity<Staff> updateMarsupial(@RequestBody Staff staff, @PathVariable Integer id) {
		Staff responseBody = this.service.updateStaff(staff, id);
		return new ResponseEntity<Staff>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeStaff/{id}") // 204 - No content
	public ResponseEntity<?> deleteStaff(@PathVariable Integer id) {
		boolean deleted = this.service.deleteStaff(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
