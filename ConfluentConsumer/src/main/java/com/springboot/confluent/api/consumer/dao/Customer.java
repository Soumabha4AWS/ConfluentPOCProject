package com.springboot.confluent.api.consumer.dao;

public class Customer {
	
	private String firstName;
	private String lastName;
	private int age;
	private float height;
	private float weight;
	private boolean automatedEmail;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String lastName, int age, float height, float weight, boolean automatedEmail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.automatedEmail = automatedEmail;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public boolean isAutomatedEmail() {
		return automatedEmail;
	}

	public void setAutomatedEmail(boolean automatedEmail) {
		this.automatedEmail = automatedEmail;
	}

}
