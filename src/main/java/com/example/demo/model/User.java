package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
@JsonInclude(Include.NON_NULL)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 30)
	@JsonProperty("first_name")
	private String firstName;
	
	@Column(length=30)
	@JsonProperty("last_name")
	private String lastName;
	
	@Column(length=20)
	private String email;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "gender", column = @Column(name = "user_gender"))
		@AttributeOverride(name = "phone", column = @Column(name = "user_phone"))

	})
	private PersonContact personContact;

	@Transient
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;
	
	public User(String firstName, String lastName, String email, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
