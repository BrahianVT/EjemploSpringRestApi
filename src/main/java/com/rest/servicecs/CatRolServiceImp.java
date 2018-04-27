package com.rest.servicecs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.dao.CatRolDao;
import com.rest.model.CatRol;

@Service
@Transactional
public class CatRolServiceImp implements CatRolService{

	@Autowired
	CatRolDao catRolDao;
	
	@Override
	public void guardar(CatRol rol) {
		catRolDao.guardar(rol);
	}

	@Override
	public CatRol getRolById(int idRol) {
		return catRolDao.getRolById(idRol);
	}

	@Override
	public List<CatRol> getListaRol() {
		return catRolDao.getListaRol();
	}

}
