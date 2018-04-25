package com.rest.dao;


import java.util.List;

import com.rest.model.Customer;


public interface CustomerDao {
Customer CustomerfindById(long id);
	
	Customer findByName(String name);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(long id);
	
	List<Customer> customerList();
	
	void deleteAllCustomer();
	
	boolean customerExists(Customer customer);
}
