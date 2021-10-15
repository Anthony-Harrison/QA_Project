package com.ah.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Spring knows this is a table.
public class Customer {

	@Id // This is the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Makes the primary key auto increment.
	private Integer id;
	private String name;
	private boolean monthlyPass;

	public Customer() {
		super();
	}

	public Customer(Integer id, String name, boolean monthlyPass) {
		super();
		this.id = id;
		this.name = name;
		this.monthlyPass = monthlyPass;
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

	public boolean isMonthlyPass() {
		return monthlyPass;
	}

	public void setMonthlyPass(boolean monthlyPass) {
		this.monthlyPass = monthlyPass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, monthlyPass, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id) && monthlyPass == other.monthlyPass && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", monthlyPass=" + monthlyPass + "]";
	}

}
