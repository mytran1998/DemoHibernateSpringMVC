package edu.fa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "Course") // Dung cho mapping, JPA query
@Table(name = "Course") // Ten trong DB
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Course_Name")
	private String name;
	
	//@Transient // Khong luu vao DB
	@Temporal(TemporalType.DATE) // Dinh dang ngay thang nam
	private Date createDate;
	
	//@Embedded
	//private Syllabus syllabus;
	
	@ElementCollection
	private List<Syllabus> lstSyllabus = new ArrayList<Syllabus>();
	
	public Course() {
		super();
	}
	
	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Course(String name) {
		this.name = name;
	}
	
	public Course(String name, Date createDate) {
		super();
		this.name = name;
		this.createDate = createDate;
	}
	
	public Course(String name, Date createDate, List<Syllabus> lstSyllabus) {
		super();
		this.name = name;
		this.createDate = createDate;
		this.lstSyllabus = lstSyllabus;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Syllabus> getLstSyllabus() {
		return lstSyllabus;
	}

	public void setLstSyllabus(List<Syllabus> lstSyllabus) {
		this.lstSyllabus = lstSyllabus;
	}
	
	
	
}
