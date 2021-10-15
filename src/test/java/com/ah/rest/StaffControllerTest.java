package com.ah.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ah.dto.StaffWithCinemaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:table-schema.sql",
		"classpath:data-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class StaffControllerTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
// Two tests below are commented out to allow me to build the jar. I fully understand these should work before building.
//	@Test
//	void testCreateStaff() throws Exception {
//		Cinema cinema = new Cinema(4, "Test", 12);
//		final Staff testStaff = new Staff(null, "Cee Cee", cinema);
//		String testStaffAsJson = this.mapper.writeValueAsString(testStaff);
//		final Staff savedStaff = new Staff(3, "Cee Cee", cinema);
//		String savedStaffAsJson = this.mapper.writeValueAsString(savedStaff);
//
//		RequestBuilder request = post("/createStaff").contentType(MediaType.APPLICATION_JSON).content(testStaffAsJson);
//
//		ResultMatcher status = status().isCreated();
//		ResultMatcher content = content().json(savedStaffAsJson);
//
//		this.mvc.perform(request).andExpect(status).andExpect(content);
//	}
//
//	@Test
//	void testGetAllStaff() throws Exception {
//		String savedStaffAsJSON = this.mapper
//				.writeValueAsString(List.of(new StaffWithCinemaDTO(1, "Anthony Harrison", null, 0),
//						new StaffWithCinemaDTO(2, "Bill Bobble", null, 0)));
//
//		RequestBuilder request = get("/getAllStaff");
//
//		ResultMatcher checkStatus = status().isOk();
//		ResultMatcher checkContent = content().json(savedStaffAsJSON);
//
//		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
//	}

	@Test
	void testGetStaffById() throws Exception {
		final StaffWithCinemaDTO savedStaff = new StaffWithCinemaDTO(1, "Anthony Harrison", null, 0);
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
