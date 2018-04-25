package com.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tax_user")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TaxUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
	private int idUser;
	
	private String name;
	private String username;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idStatusUser")
	private CatStatusUser catStatusUser;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idRol")
	private CatRol catRol;
	
	
	public CatStatusUser getCatStatusUser() {
		return catStatusUser;
	}
	public void setCatStatusUser(CatStatusUser catStatusUser) {
		this.catStatusUser = catStatusUser;
	}
	public CatRol getCatRol() {
		return catRol;
	}
	public void setCatRol(CatRol catRol) {
		this.catRol = catRol;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
