package edu.fa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Fresher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Course> courses = new ArrayList<Course>();
	
	@ManyToMany
	private Set<Group> groups = new HashSet<Group>();
	
	public Fresher() {
		super();
	}
	
	public Fresher(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public Fresher(String name, List<Course> courses) {
		super();
		this.name = name;
		this.courses = courses;
	}
	
	public Fresher(String name, Set<Group> groups) {
		super();
		this.name = name;
		this.groups = groups;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	
	
}
