package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;

//To do
@Entity
public class ClassicalGuitar extends Guitar {

	private static final long serialVersionUID = -7481031865628104637L;
	private final String stringMaterial = "Nylon";

	public ClassicalGuitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings,
			String wood, Integer numberOfFrets, String colour, String neckWood, String madeIn) {
		super(id, name, brand, type, numberOfStrings, wood, numberOfFrets, colour, neckWood, madeIn);
		// TODO Auto-generated constructor stub
	}

	public String getStringMaterial() {
		return stringMaterial;
	}

}
