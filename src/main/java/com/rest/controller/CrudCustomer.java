package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest.model.Customer;
import com.rest.servicecs.CustomerService;


@RestController
public class CrudCustomer {


	@Autowired
	private CustomerService customerService;
	
	
	/*
	@RequestMapping(value="/customer/new/",method=RequestMethod.POST)
	public @ResponseBody Customer add(@RequestBody Customer cus){
		customerService.saveCustomer(cus);
		return cus;
	}
	
	@RequestMapping(value="/customers",method=RequestMethod.GET,headers="Accept-application/json")
	public @ResponseBody List<Customer> listCustomer(){
		List<Customer> customers = customerService.customerList();
		return customers;
	}
	
	@RequestMapping(value="/customer/update/{id}",method=RequestMethod.PUT)
	public @ResponseBody Customer updateCustomer(@PathVariable("id") int id,@RequestBody Customer cus){
		cus.setId(id);
		customerService.updateCustomer(cus);
		return cus;
	}
	
	@RequestMapping(value="/customer/find/{id}",method=RequestMethod.GET,headers="Accept-application/json")
	public @ResponseBody Customer findCustomer(@PathVariable("id")int id){
		Customer cus = customerService.CustomerfindById(id);
		if(cus == null)return null;
		return cus;
	}
	
	@RequestMapping(value="/customer/delete/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Customer deleteCustomer(@PathVariable("id") long id){
		Customer customer = customerService.CustomerfindById(id);
		if(customer==null)return null;
		
		customerService.deleteCustomer(id);
		return customer;
	}
	
	@RequestMapping(value="/deleteCustomers/",method=RequestMethod.DELETE)
		public @ResponseBody String deleteAll(){
		customerService.deleteAllCustomer();
		return "All customer was deleted";
	}
	
	*/
	
	@RequestMapping("hi")
	@ResponseBody
	public String hi() {
		return "Hello, world2!!";
	}
	
	  @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<Customer>> listAllUsers() {
	        List<Customer> users = customerService.customerList();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Customer>>(users, HttpStatus.OK);
	    }
	  
	   
	    @RequestMapping(value = "/userNew/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Customer user, UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getName());
	  
	        if (customerService.customerExists(user)) {
	            System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	  
	        customerService.saveCustomer(user);
	  
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	    
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Customer> updateUser(@PathVariable("id") long id, @RequestBody Customer user) {
	        System.out.println("Updating User " + id);
	          
	        Customer currentUser = customerService.CustomerfindById(id);
	          
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	  
	          
	        customerService.updateCustomer(currentUser);
	        return new ResponseEntity<Customer>(currentUser, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Customer> getUser(@PathVariable("id") long id) {
	        System.out.println("Fetching User with id " + id);
	        Customer user = customerService.CustomerfindById(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Customer>(user, HttpStatus.OK);
	    }
	  
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Customer> deleteUser(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	  
	        Customer user = customerService.CustomerfindById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	  
	        customerService.deleteCustomer(id);
	        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	    }
	  
	    @RequestMapping(value = "/userdel", method = RequestMethod.DELETE)
	    public ResponseEntity<Customer> deleteAllUsers() {
	        System.out.println("Deleting All Users");
	  
	        customerService.deleteAllCustomer();
	        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	    }
	  
	      
}
