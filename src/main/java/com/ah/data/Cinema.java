package com.ah.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cinema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String branch;
	private int noOfScreens;

	public Cinema() {
		super();
	}

	public Cinema(Integer id, String branch, int noOfScreens) {
		super();
		this.id = id;
		this.branch = branch;
		this.noOfScreens = noOfScreens;
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

	@Override
	public int hashCode() {
		return Objects.hash(branch, id, noOfScreens);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cinema other = (Cinema) obj;
		return Objects.equals(branch, other.branch) && Objects.equals(id, other.id) && noOfScreens == other.noOfScreens;
	}

	@Override
	public String toString() {
		return "Cinema [id=" + id + ", branch=" + branch + ", noOfScreens=" + noOfScreens + "]";
	}

}
