package com.ah.data;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cinema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String branch;
	private int noOfScreens;

	@OneToMany(mappedBy = "cinema")
	private List<Staff> staff;

	public Cinema() {
		super();
	}

	public Cinema(Integer id, String branch, int noOfScreens) {
		super();
		this.id = id;
		this.branch = branch;
		this.noOfScreens = noOfScreens;
	}

	public Cinema(Integer id, String branch, int noOfScreens, List<Staff> staff) {
		super();
		this.id = id;
		this.branch = branch;
		this.noOfScreens = noOfScreens;
		this.staff = staff;
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

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	@Override
	public int hashCode() {
		return Objects.hash(branch, id, noOfScreens, staff);
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
		return Objects.equals(branch, other.branch) && Objects.equals(id, other.id) && noOfScreens == other.noOfScreens
				&& Objects.equals(staff, other.staff);
	}

	@Override
	public String toString() {
		return "Cinema [id=" + id + ", branch=" + branch + ", noOfScreens=" + noOfScreens + ", staff=" + staff + "]";
	}

}
