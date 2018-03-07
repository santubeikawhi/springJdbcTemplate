package com.jl.spring.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jl.spring.model.Customer;
import com.jl.spring.model.CustomerRowMapper;

public class JdbcSupportCustomerDao  extends JdbcDaoSupport implements CustomerDao {

	@Override
	public void batchInsert(final List<Customer> customers) {
		String sql = "insert into sys.customer (CUST_ID, NAME_, AGE_) values (?,?,?)";
		//利用getJdbcTemplate 直接获取数据库连接模板
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {			
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

	@Override
	public void insert(Customer customer) {
		String sql = "insert into sys.customer (CUST_ID, NAME_, AGE_) values (?,?,?) ";
		getJdbcTemplate().update(sql,new Object[]{customer.getCustId(),customer.getName(),customer.getAge()});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomerById(String custId) {
		Customer customer = new Customer();
		String sql = "select * from sys.customer where cust_id = ?";
		customer = (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{custId}, new CustomerRowMapper());
		return customer;
	}

}
