package com.jl.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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

	@Override
	public void batchInsert(final List<Customer> customers) {
		// TODO Auto-generated method stub
		String sql = "insert into sys.customer (CUST_ID, NAME_, AGE_) values (?,?,?)";
		jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = customers.get(i);
				ps.setString(1, customer.getCustId());
				ps.setString(2, customer.getName());
				ps.setInt(3, customer.getAge() );
			}
					
			@Override
			public int getBatchSize() {
				return customers.size();
			}
		});
	}

}
