package com.cucumber.demo.dto;

import java.io.Serializable;

public class NumToStringDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Double number;
	private String stringOfNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getStringOfNumber() {
		return stringOfNumber;
	}

	public void setStringOfNumber(String stringOfNumber) {
		this.stringOfNumber = stringOfNumber;
	}

	@Override
	public String toString() {
		return "NumToStringDTO [name=" + name + ", number=" + number + ", stringOfNumber=" + stringOfNumber + "]";
	}

}