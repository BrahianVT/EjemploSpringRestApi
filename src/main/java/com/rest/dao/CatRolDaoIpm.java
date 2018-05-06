package com.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.CatRol;

@Repository
public class CatRolDaoIpm implements CatRolDao {

	@Autowired
	SessionFactory sesionFactory;
	
	public Session getSession(){return sesionFactory.getCurrentSession();}
	@Override
	public void guardar(CatRol rol) {
		getSession().saveOrUpdate(rol);
	}

	@Override
	public CatRol getRolById(int idRol) {
		CatRol rolEncontrado = getSession().get(CatRol.class,idRol);
		return (rolEncontrado != null)?rolEncontrado:null;
	}

	@Override
	public List<CatRol> getListaRol() {
		return (List<CatRol>)getSession().createQuery("from CatRol",CatRol.class).getResultList();
	}
	@Override
	public void borrar(CatRol rol) {
		getSession().delete(rol);
		
	}

}
