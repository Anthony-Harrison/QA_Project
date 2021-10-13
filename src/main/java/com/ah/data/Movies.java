package com.ah.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Spring knows this is a table.
public class Movies {

	@Id // This is the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Makes the primary key auto increment.
	private Integer id;
	private String title;
	private int runtime;

	public Movies() {
		super();
	}

	public Movies(Integer id, String title, int runtime) {
		super();
		this.id = id;
		this.title = title;
		this.runtime = runtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, runtime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movies other = (Movies) obj;
		return Objects.equals(id, other.id) && Objects.equals(title, other.title) && runtime == other.runtime;
	}

	@Override
	public String toString() {
		return "Movies [id=" + id + ", title=" + title + ", runtime=" + runtime + "]";
	}

}
