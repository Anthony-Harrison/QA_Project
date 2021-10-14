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

import com.ah.data.Staff;
import com.ah.dto.StaffWithCinemaDTO;
import com.ah.service.StaffService;

@RestController
public class StaffController {

	private StaffService service;

	public StaffController(StaffService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createStaff")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
		Staff responseBody = this.service.createStaff(staff);
		ResponseEntity<Staff> response = new ResponseEntity<Staff>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateStaff/{id}")
	public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff, @PathVariable Integer id) {
		Staff responseBody = this.service.updateStaff(staff, id);
		return new ResponseEntity<Staff>(responseBody, HttpStatus.ACCEPTED);
	}

	@GetMapping("getStaffById/{id}")
	public StaffWithCinemaDTO getStaffById(@PathVariable Integer id) {
		return this.service.getStaffById(id);
	}

	@GetMapping("/getAllStaff")
	public List<StaffWithCinemaDTO> getAllStaff() {
		return this.service.getAllStaff();
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
