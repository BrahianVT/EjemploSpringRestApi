package com.rest.dao;

import java.util.List;

import com.rest.model.CatRol;

public interface CatRolDao {

	public void guardar(CatRol rol);
	public CatRol getRolById(int idRol);
	public List<CatRol> getListaRol();
}
