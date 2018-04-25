package com.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Customer;

@Repository
public class CustomerDaoImp implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public Customer CustomerfindById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		try{
			customer = (Customer) session.get(Customer.class, id);
			transaction.commit();
			session.close();
		}catch(Exception ex){
			transaction.rollback();
			session.close();
			ex.printStackTrace();
		}
		return customer;
	}

	@SuppressWarnings("deprecation")
	public Customer findByName(String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		String consulta = "from com.rest.model.Customer where name = ?";
		try{
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(consulta);
			query.setParameter(0, name);
			customer = (Customer) query.uniqueResult();
			transaction.commit();
			session.close();
		}catch(Exception ex){
			transaction.rollback();
			session.close();
			ex.printStackTrace();
		}
		return customer;
	}

	
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(customer != null){
			try{
				session.save(customer);
				transaction.commit();
			}catch(Exception ex){
				transaction.rollback();
				session.close();
				ex.printStackTrace();
			}
		}
	}

	public void updateCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if(customer != null){
			try{
				session.update(customer);
				transaction.commit();
			}catch(Exception ex){
				transaction.rollback();
				session.close();
				ex.printStackTrace();
			}
		}
	}

	public void deleteCustomer(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		try{
			customer = (Customer) session.get(Customer.class, id);
			session.delete(customer);
			transaction.commit();
		}catch(Exception ex){
			transaction.rollback();
			session.close();
			ex.printStackTrace();
		}
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Customer> customerList() {
		List<Customer> customer = new ArrayList<Customer>();
		Session session = sessionFactory.openSession();
		customer = session.createQuery("from com.rest.model.Customer").list();
		return customer;
	}

	
	public void deleteAllCustomer() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.createQuery("delete from Customer").executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception ex){
			transaction.rollback();
			session.close();
			ex.printStackTrace();
		}
	}

	public boolean customerExists(Customer customer) {
		return findByName(customer.getName())!= null;
	}

}
