package com.rest.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.TaxUser;
@Repository
public class TaxUserDaoImp implements TaxUserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){ return sessionFactory.getCurrentSession();}
	@Override
	public List<TaxUser> getListUser() {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<TaxUser> query = builder.createQuery(TaxUser.class); 
		Root<TaxUser> root = query.from(TaxUser.class);
		query.select(root);
		Query<TaxUser> q = getSession().createQuery(query);
		return q.getResultList();
	}

	@Override
	public void saveOrUpdate(TaxUser taxuser) {
		getSession().saveOrUpdate(taxuser);
	}

	@Override
	public void deleteUser(int id) {
		TaxUser user = getSession().get(TaxUser.class, id);
		getSession().delete(user);
	}

	@Override
	public TaxUser findUserbyId(int id) {
		return (TaxUser) getSession().get(TaxUser.class, id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int findId(String username, String contra) {
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("SELECT e.idUser FROM TaxUser e WHERE e.username=:username and e.password=:password");
		query.setParameter("username", username);
		query.setParameter("password", contra);
		return (int)query.uniqueResult();
	}

}
