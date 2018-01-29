package com.jl.spring.model;

public class Customer {
	public String custId;
	public String name;
	public int age;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String custId, String name, int age) {
		super();
		this.custId = custId;
		this.name = name;
		this.age = age;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
