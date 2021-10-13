package com.ah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ah.data.Customer;
import com.ah.repo.CustomerRepo;

@Service
public class CustomerService {
	private CustomerRepo repo;

	public CustomerService(CustomerRepo repo) {
		super();
		this.repo = repo;
	}

	public Customer getCustomerById(Integer id) {
		return this.repo.findById(id).get();
	}

	public List<Customer> getAllCustomers() {
		return this.repo.findAll();
	}

	public Customer createCustomer(Customer customer) {
		return this.repo.save(customer);
	}

	public Customer updateCustomer(Customer customer, Integer id) {
		Optional<Customer> optionalCustomer = this.repo.findById(id);
		Customer toUpdate = optionalCustomer.get();
		toUpdate.setName(customer.getName());
		toUpdate.setMonthlyPass(customer.isMonthlyPass());

		return this.repo.save(toUpdate);
	}

	public boolean deleteCustomer(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
}
