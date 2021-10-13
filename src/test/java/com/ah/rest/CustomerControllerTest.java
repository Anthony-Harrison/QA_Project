package com.ah.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.ah.data.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:customer-schema.sql",
		"classpath:customer-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CustomerControllerTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreateCustomer() throws Exception {
		final Customer testCustomer = new Customer(null, "Cee Cee", true);
		String testCustomerAsJson = this.mapper.writeValueAsString(testCustomer);
		final Customer savedCustomer = new Customer(3, "Cee Cee", true);
		String savedCustomerAsJson = this.mapper.writeValueAsString(savedCustomer);

		RequestBuilder request = post("/createCustomer").contentType(MediaType.APPLICATION_JSON)
				.content(testCustomerAsJson);

		ResultMatcher status = status().isCreated();
		ResultMatcher content = content().json(savedCustomerAsJson);

		this.mvc.perform(request).andExpect(status).andExpect(content);
	}

	@Test
	void testGetAllCustomers() throws Exception {
		String savedCustomerAsJSON = this.mapper.writeValueAsString(
				List.of(new Customer(1, "Anthony Harrison", true), new Customer(2, "Bill Bobble", false)));

		RequestBuilder request = get("/getAllCustomers");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCustomerAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetCustomerById() throws Exception {
		final Customer savedCustomer = new Customer(2, "Bill Bobble", false);
		String savedCustomerAsJSON = this.mapper.writeValueAsString(savedCustomer);

		RequestBuilder request = get("/getCustomerById/" + savedCustomer.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCustomerAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdateCustomer() throws Exception {
		final Customer testCustomer = new Customer(1, "Anthony Harrison", false);
		final String testCustomerAsJSON = this.mapper.writeValueAsString(testCustomer);

		RequestBuilder request = put("/updateCustomer/1").contentType(MediaType.APPLICATION_JSON)
				.content(testCustomerAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testCustomerAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDeleteCustomer() throws Exception {
		this.mvc.perform(delete("/removeCustomer/1")).andExpect(status().isNoContent());
	}

}
