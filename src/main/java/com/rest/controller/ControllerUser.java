package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest.model.TaxUser;
import com.rest.servicecs.TaxUserService;
import com.rest.utilities.WrapperClass;


@RestController
public class ControllerUser {

	@Autowired
	private TaxUserService taxUserService;
	
	public void setTaxUserService(TaxUserService taxUserService){this.taxUserService = taxUserService;}
	
	@RequestMapping(value = "/listaUsuarios/", method = RequestMethod.GET)
	  public ResponseEntity<List<TaxUser>> listUsers() {
		List<TaxUser> users = taxUserService.getListUser();
		if(users == null)
			return new ResponseEntity<List<TaxUser>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<TaxUser>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/crearActualizarUsuario/", method = RequestMethod.POST)
	public ResponseEntity<TaxUser> createorUpdateUser(@RequestBody TaxUser user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());
		taxUserService.saveOrUpdate(user);
		System.out.println("Se realizo correctamente");
		return new ResponseEntity<TaxUser>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/iniciarSession/{user}/{contra}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<TaxUser> obtenerSesion(@PathVariable("user") String user, @PathVariable("contra") String contra){
		System.out.println("Entro al servicio");	
		int id;
			id = taxUserService.findId(user, contra);
			if(id == 0 ){
				return new ResponseEntity<TaxUser>(HttpStatus.NOT_FOUND);
			}
			TaxUser usuario = taxUserService.findUserbyId(id);
			return new ResponseEntity<TaxUser>(usuario,HttpStatus.OK);
		}

	
	
	 //-----------------------------------------------------------------------------------
	@RequestMapping(value = "/listaUsuarios2/", method = RequestMethod.GET)
	  public ResponseEntity<WrapperClass> listUsers2() {
		
		List<TaxUser> users = taxUserService.getListUser();
		WrapperClass <List<TaxUser>> usersResponse =new  WrapperClass<List<TaxUser>>() ;
		
		if(users == null){
			usersResponse.setErroCode("1");
			usersResponse.setMessage("La lista esta vacia");
			return new ResponseEntity<WrapperClass>(HttpStatus.NO_CONTENT);
			}
		else{
			usersResponse.setErroCode("0");
			usersResponse.setMessage("Lista retornada correctamente");
			usersResponse.setData(users);
			return new ResponseEntity<WrapperClass>(usersResponse,HttpStatus.OK);
		}
	}
}
