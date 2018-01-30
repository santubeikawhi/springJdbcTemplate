package com.jl.spring.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jl.spring.dao.CustomerDao;
import com.jl.spring.model.Customer;

public class Webapp {
	public static void main(String args[]){
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Model.xml");
		CustomerDao customerDao = (CustomerDao)applicationContext.getBean("customerDao");
		//单个数据插入和查询
		/*customerDao.insert(new Customer("test88","harry",11));
		Customer cd2 = customerDao.findCustomerById("test1");
		System.out.println(cd2.getName());
		*/
		
		//多个数据的插入
		List<Customer> customers = new ArrayList<Customer>();
		for(int ci=0;ci<=3;ci++){
			Customer customerIndex = new Customer("batchInsert"+ci,"jl"+ci,20+ci);
			customers.add(customerIndex);
		}
		customerDao.batchInsert(customers);
		Customer cd2 = customerDao.findCustomerById("batchInsert0");
		System.out.println(cd2.getName());
	}
}
