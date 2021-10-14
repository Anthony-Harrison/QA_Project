package com.ah.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StaffDTOTest {

	@Test
	public void testEmptyStaff() {
		StaffDTO s = new StaffDTO();
		StaffDTO expected = new StaffDTO(null, null);
		assertEquals(expected, s);
	}

	@Test
	public void testGetId() {
		StaffDTO staff = new StaffDTO(1, "a");
		Integer expected = 1;
		assertEquals(expected, staff.getId(), 0);
	}

	@Test
	public void testGetName() {
		StaffDTO staff = new StaffDTO(1, "a");
		String expected = "a";
		assertEquals(expected, staff.getName());
	}

	@Test
	public void testSetName() {
		StaffDTO staff = new StaffDTO(1, "a");
		staff.setName("b");
		String expected = "b";
		assertEquals(expected, staff.getName());
	}

	@Test
	public void testToString() {
		StaffDTO staff = new StaffDTO(1, "a");
		String expected = "StaffDTO [id=" + staff.getId() + ", name=" + staff.getName() + "]";
		assertEquals(expected, staff.toString());
	}

}
