package com.ah.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ah.data.Cinema;
import com.ah.data.Staff;
import com.ah.dto.StaffWithCinemaDTO;
import com.ah.repo.StaffRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StaffServiceTest {

	@Autowired
	private StaffService service;

	@MockBean
	private StaffRepo repo;

	@Test
	void testCreateStaff() {
		Staff newStaff = new Staff(null, "Staff 1");
		Staff savedStaff = new Staff(3, "Staff 1");
		Mockito.when(this.repo.save(newStaff)).thenReturn(savedStaff);
		assertThat(this.service.createStaff(newStaff).equals(savedStaff));

		Mockito.verify(this.repo, Mockito.times(1)).save(newStaff);

	}

	@Test
	void testUpdateStaff() {
		final Integer id = 1;
		Staff staff = new Staff(id, "Staff 1");
		Optional<Staff> optionalStaff = Optional.of(staff);

		Staff newStaff = new Staff(id, "Updated staff");

		Mockito.when(this.repo.findById(id)).thenReturn(optionalStaff);
		Mockito.when(this.repo.save(newStaff)).thenReturn(newStaff);

		assertThat(this.service.updateStaff(newStaff, staff.getId())).isEqualTo(newStaff);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newStaff);
	}

	@Test
	void testGetById() {
		final Integer id = 1;
		Cinema savedCinema = new Cinema(1, null, 0, null);
		final Staff staff = new Staff(id, "Anthony Harrison", savedCinema);
		StaffWithCinemaDTO check = this.service.mapDTO(staff);
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(staff));

		assertThat(this.service.getStaffById(id)).isEqualTo(check);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetAllStaff() {
		Cinema savedCinema = new Cinema(1, null, 0, null);
		final List<Staff> staffs = List.of(new Staff(1, "Anthony Harrison", savedCinema),
				new Staff(2, "Bill Bobble", savedCinema));
		List<StaffWithCinemaDTO> check = new ArrayList<>();
		for (Staff staff : staffs) {
			check.add(this.service.mapDTO(staff));
		}
		Mockito.when(this.repo.findAll()).thenReturn(staffs);

		assertThat(this.service.getAllStaff()).isEqualTo(check);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteStaff(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
