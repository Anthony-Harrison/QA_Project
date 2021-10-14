package com.ah.dto;

public class StaffWithCinemaDTO {
	private Integer id;
	private String name;
	private String branch;
	private int noOfScreens;

	public StaffWithCinemaDTO() {
		super();
	}

	public StaffWithCinemaDTO(Integer id, String name, String branch, int noOfScreens) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.noOfScreens = noOfScreens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
