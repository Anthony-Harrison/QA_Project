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

import com.ah.data.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:staff-schema.sql",
		"classpath:staff-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StaffControllerTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreateStaff() throws Exception {
		final Staff testStaff = new Staff(null, "Cee Cee");
		String testStaffAsJson = this.mapper.writeValueAsString(testStaff);
		final Staff savedStaff = new Staff(3, "Cee Cee");
		String savedStaffAsJson = this.mapper.writeValueAsString(savedStaff);

		RequestBuilder request = post("/createStaff").contentType(MediaType.APPLICATION_JSON).content(testStaffAsJson);

		ResultMatcher status = status().isCreated();
		ResultMatcher content = content().json(savedStaffAsJson);

		this.mvc.perform(request).andExpect(status).andExpect(content);
	}

	@Test
	void testGetAllStaff() throws Exception {
		String savedStaffAsJSON = this.mapper
				.writeValueAsString(List.of(new Staff(1, "Anthony Harrison"), new Staff(2, "Bill Bobble")));

		RequestBuilder request = get("/getAllStaff");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedStaffAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetStaffById() throws Exception {
		final Staff savedStaff = new Staff(2, "Bill Bobble");
		String savedStaffAsJSON = this.mapper.writeValueAsString(savedStaff);

		RequestBuilder request = get("/getStaffById/" + savedStaff.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedStaffAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdateStaff() throws Exception {
		final Staff testStaff = new Staff(1, "Anthony update");
		final String testStaffAsJSON = this.mapper.writeValueAsString(testStaff);

		RequestBuilder request = put("/updateStaff/1").contentType(MediaType.APPLICATION_JSON).content(testStaffAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testStaffAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDeleteStaff() throws Exception {
		this.mvc.perform(delete("/removeStaff/1")).andExpect(status().isNoContent());
	}

}
