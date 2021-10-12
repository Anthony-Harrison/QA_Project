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

import com.ah.data.Cinema;
import com.ah.repo.CinemaRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CinemaServiceTest {

	@Autowired
	private CinemaService service;

	@MockBean
	private CinemaRepo repo;

	@Test
	void testCreateCinema() {
		Cinema newCinema = new Cinema(null, "Cinema 1", 12);
		Cinema savedCinema = new Cinema(4, "Cinema 1", 12);
		Mockito.when(this.repo.save(newCinema)).thenReturn(savedCinema);
		assertThat(this.service.createCinema(newCinema).equals(savedCinema));

		Mockito.verify(this.repo, Mockito.times(1)).save(newCinema);

	}

	@Test
	void testUpdateCinema() {
		final Integer id = 1;
		Cinema cinema = new Cinema(id, "Cinema 1", 12);
		Optional<Cinema> optionalCinema = Optional.of(cinema);

		Cinema newCinema = new Cinema(id, "Updated cinema", 15);

		Mockito.when(this.repo.findById(id)).thenReturn(optionalCinema);
		Mockito.when(this.repo.save(newCinema)).thenReturn(newCinema);

		assertThat(this.service.updateCinema(newCinema, cinema.getId())).isEqualTo(newCinema);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newCinema);
	}

	@Test
	void testGetById() {
		final Integer id = 1;
		final Cinema cinema = new Cinema(id, "Cinema 1", 12);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(cinema));

		assertThat(this.service.getCinemaById(id)).isEqualTo(cinema);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAllCinemas() {
		final List<Cinema> cinemas = List.of(new Cinema(4, "Cinema 1", 12), new Cinema(5, "Cinema 1", 12));

		Mockito.when(this.repo.findAll()).thenReturn(cinemas);

		assertThat(this.service.getAllCinemas()).isEqualTo(cinemas);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteCinema(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
