package com.example.demo.model;

import jakarta.persistence.Embedded;

public class PersonContact {

	@Embedded
	private String gender;
	private String number;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
