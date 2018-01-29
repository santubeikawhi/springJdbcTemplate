package com.jl.spring.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getString("Cust_ID"));
		customer.setName(rs.getString("NAME_"));
		customer.setAge(rs.getInt("AGE_"));
		return customer;
	}

}
