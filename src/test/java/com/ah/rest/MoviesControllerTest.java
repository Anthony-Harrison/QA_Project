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

import com.ah.data.Movies;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:movies-schema.sql",
		"classpath:movies-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class MoviesControllerTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreateMovie() throws Exception {
		final Movies testMovies = new Movies(null, "It's a musical", 95);
		String testMoviesAsJson = this.mapper.writeValueAsString(testMovies);
		final Movies savedMovies = new Movies(3, "It's a musical", 95);
		String savedMoviesAsJson = this.mapper.writeValueAsString(savedMovies);

		RequestBuilder request = post("/createMovie").contentType(MediaType.APPLICATION_JSON).content(testMoviesAsJson);

		ResultMatcher status = status().isCreated();
		ResultMatcher content = content().json(savedMoviesAsJson);

		this.mvc.perform(request).andExpect(status).andExpect(content);
	}

	@Test
	void testGetAllMovies() throws Exception {
		String savedMoviesAsJSON = this.mapper
				.writeValueAsString(List.of(new Movies(1, "Generic Action", 123), new Movies(2, "Scary Mystery", 180)));

		RequestBuilder request = get("/getAllMovies");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedMoviesAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetMoviesById() throws Exception {
		final Movies savedMovies = new Movies(2, "Scary Mystery", 180);
		String savedMoviesAsJSON = this.mapper.writeValueAsString(savedMovies);

		RequestBuilder request = get("/getMoviesById/" + savedMovies.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedMoviesAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdateMovies() throws Exception {
		final Movies testMovies = new Movies(1, "Generic Action", 123);
		final String testMoviesAsJSON = this.mapper.writeValueAsString(testMovies);

		RequestBuilder request = put("/updateMovies/1").contentType(MediaType.APPLICATION_JSON)
				.content(testMoviesAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testMoviesAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDeleteMovies() throws Exception {
		this.mvc.perform(delete("/removeMovies/1")).andExpect(status().isNoContent());
	}

}
