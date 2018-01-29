package com.jl.spring.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.jl.spring.model.Customer;
import com.jl.spring.model.CustomerRowMapper;

public class JdbcCustomerDao implements CustomerDao{

	private static JdbcCustomerDao instance = new JdbcCustomerDao();
	
	private JdbcCustomerDao(){};
	
	public static JdbcCustomerDao getInstance(){
      return instance;
   }
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Customer customer) {
		String sql = "insert into sys.customer (CUST_ID, NAME_, AGE_) values (?,?,?) ";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sql,new Object[]{customer.getCustId(),customer.getName(),customer.getAge()});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomerById(String custId) {
		Customer customer = new Customer();
		String sql = "select * from sys.customer where cust_id = ?";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		customer = (Customer)jdbcTemplate.queryForObject(sql, new Object[]{custId}, new CustomerRowMapper());
		return customer;
	}

}
