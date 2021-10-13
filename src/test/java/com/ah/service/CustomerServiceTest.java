package com.ah.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ah.data.Customer;
import com.ah.repo.CustomerRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepo repo;

	@Test
	void testCreateCustomer() {
		Customer newCustomer = new Customer(null, "Customer 1", true);
		Customer savedCustomer = new Customer(3, "Customer 1", true);
		Mockito.when(this.repo.save(newCustomer)).thenReturn(savedCustomer);
		assertThat(this.service.createCustomer(newCustomer).equals(savedCustomer));

		Mockito.verify(this.repo, Mockito.times(1)).save(newCustomer);

	}

	@Test
	void testUpdateCustomer() {
		final Integer id = 1;
		Customer customer = new Customer(id, "Customer 1", true);
		Optional<Customer> optionalCustomer = Optional.of(customer);

		Customer newCustomer = new Customer(id, "Updated customer", true);

		Mockito.when(this.repo.findById(id)).thenReturn(optionalCustomer);
		Mockito.when(this.repo.save(newCustomer)).thenReturn(newCustomer);

		assertThat(this.service.updateCustomer(newCustomer, customer.getId())).isEqualTo(newCustomer);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newCustomer);
	}

	@Test
	void testGetById() {
		final Integer id = 1;
		final Customer customer = new Customer(id, "Anthony Harrison", true);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(customer));

		assertThat(this.service.getCustomerById(id)).isEqualTo(customer);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAllCustomers() {
		final List<Customer> customers = List.of(new Customer(1, "Anthony Harrison", true),
				new Customer(2, "Bill Bobble", false));

		Mockito.when(this.repo.findAll()).thenReturn(customers);

		assertThat(this.service.getAllCustomers()).isEqualTo(customers);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteCustomer(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
