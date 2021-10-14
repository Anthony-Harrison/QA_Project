package com.ah.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity // Spring knows this is a table.
public class Staff {

	@Id // This is the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Makes the primary key auto increment.
	private Integer id;
	private String name;

	@ManyToOne
	private Cinema cinema;

	public Staff() {
		super();
	}

	public Staff(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Staff(Integer id, String name, Cinema cinema) {
		super();
		this.id = id;
		this.name = name;
		this.cinema = cinema;
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

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cinema, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(cinema, other.cinema) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", cinema=" + cinema + "]";
	}

}
