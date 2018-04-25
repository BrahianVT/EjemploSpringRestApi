package com.rest.servicecs;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.dao.CustomerDao;
import com.rest.model.Customer;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService{
	
	private CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao){
		this.customerDao = customerDao;
	}
	
	
	public Customer CustomerfindById(long id) {
		
		return customerDao.CustomerfindById(id);
	}

	
	public Customer findByName(String name) {
		
		return customerDao.findByName(name);
	}


	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
		
	}

	
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
		
	}


	public void deleteCustomer(long id) {
		customerDao.deleteCustomer(id);
		
	}


	public List<Customer> customerList() {
		
		return customerDao.customerList();
	}


	public void deleteAllCustomer() {
		customerDao.deleteAllCustomer();
		
	}


	public boolean customerExists(Customer customer) {
		
		return customerDao.customerExists(customer);
	}


}
