package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.CatRol;
import com.rest.servicecs.CatRolService;

@RestController
public class ControllerRol {

	@Autowired
	CatRolService catRolService;
	
	@RequestMapping(value="/listaRol", method = RequestMethod.GET)
	public ResponseEntity<List<CatRol>> obtenerListaRol(){
		List<CatRol> lista = catRolService.getListaRol();
		if(lista.isEmpty())
			return new ResponseEntity<List<CatRol>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CatRol>>(lista,HttpStatus.OK);
	}
}
