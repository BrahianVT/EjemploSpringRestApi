package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tax_cat_rol")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CatRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
	private int idRol;
	
	private String description;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
