package com.jl.spring.dao;

import java.util.List;

import com.jl.spring.model.Customer;

public interface CustomerDao {
	public void batchInsert(List<Customer> customers);
	public void insert(Customer customer);
	public Customer findCustomerById(String custId);
}
