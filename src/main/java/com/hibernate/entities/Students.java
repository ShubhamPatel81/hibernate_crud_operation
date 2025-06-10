package com.hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * 
 */
@Entity
@Table
public class Students {
	
	
//filed
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	
	@Column(name = "student_name", length = 300)
	private String name;
	
	@Column(name = "student_college", length = 600, nullable = true)
	private String college;
	
	private String phone;
	private String email;
	
	private boolean active;
	
	@Lob
	private String about;

	@OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
	private List<Certificate>certificates = new ArrayList<>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}
	
	
	
}
