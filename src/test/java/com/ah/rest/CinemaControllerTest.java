package com.ah.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.ah.data.Cinema;
import com.ah.dto.CinemaDTO;
import com.ah.dto.StaffDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:table-schema.sql",
		"classpath:data-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CinemaControllerTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreateCinema() throws Exception {
		final Cinema testCinema = new Cinema(null, "Anths cinema", 6, null);
		String testCinemaAsJson = this.mapper.writeValueAsString(testCinema);
		final Cinema savedCinema = new Cinema(4, "Anths cinema", 6, null);
		String savedCinemaAsJson = this.mapper.writeValueAsString(savedCinema);

		RequestBuilder request = post("/createCinema").contentType(MediaType.APPLICATION_JSON)
				.content(testCinemaAsJson);

		ResultMatcher status = status().isCreated();
		ResultMatcher content = content().json(savedCinemaAsJson);

		this.mvc.perform(request).andExpect(status).andExpect(content);
	}

	@Test
	void testGetAllCinemas() throws Exception {
		List<StaffDTO> staff = new ArrayList<>();
		String savedCinemaAsJSON = this.mapper.writeValueAsString(List.of(new CinemaDTO(1, "Local place", 12, staff),
				new CinemaDTO(2, "Far away", 15, staff), new CinemaDTO(3, "Very far away", 5, staff)));

		RequestBuilder request = get("/getAllCinemas");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCinemaAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetCinemaById() throws Exception {
		List<StaffDTO> staff = new ArrayList<>();
		final CinemaDTO savedCinema = new CinemaDTO(3, "Very far away", 5, staff);
		String savedCinemaAsJSON = this.mapper.writeValueAsString(savedCinema);

		RequestBuilder request = get("/getCinemaById/" + savedCinema.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedCinemaAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdateCinema() throws Exception {
		List<StaffDTO> staff = new ArrayList<>();
		final CinemaDTO testCinema = new CinemaDTO(1, "Local place", 6, staff);
		final String testCinemaAsJSON = this.mapper.writeValueAsString(testCinema);

		RequestBuilder request = put("/updateCinema/1").contentType(MediaType.APPLICATION_JSON)
				.content(testCinemaAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testCinemaAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDeleteCinema() throws Exception {
		this.mvc.perform(delete("/removeCinema/1")).andExpect(status().isNoContent());
	}

}
