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

import com.ah.data.Customer;
import com.ah.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService service;

	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}

	@GetMapping("getCustomerById/{id}")
	public Customer getCustomerById(@PathVariable Integer id) {
		return this.service.getCustomerById(id);
	}

	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers() {
		return this.service.getAllCustomers();
	}

	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer responseBody = this.service.createCustomer(customer);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
		Customer responseBody = this.service.updateCustomer(customer, id);
		return new ResponseEntity<Customer>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeCustomer/{id}") // 204 - No content
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		boolean deleted = this.service.deleteCustomer(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
