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

import com.ah.data.Movies;
import com.ah.repo.MoviesRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MoviesServiceTest {

	@Autowired
	private MoviesService service;

	@MockBean
	private MoviesRepo repo;

	@Test
	void testCreateMovies() {
		Movies newMovies = new Movies(null, "It's a musical", 95);
		Movies savedMovies = new Movies(3, "It's a musical", 95);
		Mockito.when(this.repo.save(newMovies)).thenReturn(savedMovies);
		assertThat(this.service.createMovies(newMovies).equals(savedMovies));

		Mockito.verify(this.repo, Mockito.times(1)).save(newMovies);

	}

	@Test
	void testUpdateMovies() {
		final Integer id = 1;
		Movies movies = new Movies(id, "Generic Action", 123);
		Optional<Movies> optionalMovies = Optional.of(movies);

		Movies newMovies = new Movies(id, "Generic Action Directors cut", 137);

		Mockito.when(this.repo.findById(id)).thenReturn(optionalMovies);
		Mockito.when(this.repo.save(newMovies)).thenReturn(newMovies);

		assertThat(this.service.updateMovies(newMovies, movies.getId())).isEqualTo(newMovies);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newMovies);
	}

	@Test
	void testGetById() {
		final Integer id = 1;
		final Movies movies = new Movies(id, "Generic Action", 123);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(movies));

		assertThat(this.service.getMoviesById(id)).isEqualTo(movies);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAllMovies() {
		final List<Movies> movies = List.of(new Movies(1, "Generic Action", 123), new Movies(2, "Scary Mystery", 180));

		Mockito.when(this.repo.findAll()).thenReturn(movies);

		assertThat(this.service.getAllMoviess()).isEqualTo(movies);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteMovies(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
