package com.ah.dto;

import java.util.List;

public class CinemaDTO {
	private Integer id;
	private String branch;
	private int noOfScreens;
	private List<StaffDTO> staff;

	public CinemaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getNoOfScreens() {
		return noOfScreens;
	}

	public void setNoOfScreens(int noOfScreens) {
		this.noOfScreens = noOfScreens;
	}

	public List<StaffDTO> getStaff() {
		return staff;
	}

	public void setStaff(List<StaffDTO> staff) {
		this.staff = staff;
	}

}
