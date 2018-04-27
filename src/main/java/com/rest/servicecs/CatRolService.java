package com.rest.servicecs;

import java.util.List;

import com.rest.model.CatRol;

public interface CatRolService {

	public void guardar(CatRol rol);
	public CatRol getRolById(int idRol);
	public List<CatRol> getListaRol();
}
