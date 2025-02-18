package com.uajj.Tests.model.entities;

import jakarta.persistence.Entity;

//To do
@Entity
public class ClassicalGuitar extends Guitar {

	private static final long serialVersionUID = -7481031865628104637L;
	private final String stringMaterial = "Nylon";
	
	
	public String getStringmaterial() {
		return stringMaterial;
	}
	
	

}
