package com.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tax_cat_status")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CatStatusUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStatusUser;
	
	private String description;

	public int getIdStatusUser() {
		return idStatusUser;
	}

	public void setIdStatusUser(int idStatusUser) {
		this.idStatusUser = idStatusUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
