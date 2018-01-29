package com.jl.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jl.spring.dao.CustomerDao;
import com.jl.spring.model.Customer;

public class Webapp {
	public static void main(String args[]){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Model.xml");
		CustomerDao customerDao = (CustomerDao)applicationContext.getBean("customerDao");
		customerDao.insert(new Customer("test88","harry",11));
		for(int i = 0;i<100;i++){
			Customer cd2 = customerDao.findCustomerById("test1");
			System.out.println(cd2.getName());
		}
		
	}
}
