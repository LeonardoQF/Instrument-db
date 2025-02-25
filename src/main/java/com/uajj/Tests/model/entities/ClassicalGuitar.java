package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.GuitarType;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;

import jakarta.persistence.Entity;

//To do
@Entity
public class ClassicalGuitar extends Guitar {

	private static final long serialVersionUID = -7481031865628104637L;
	private final StringMaterial stringMaterial = StringMaterial.NYLON;

	public ClassicalGuitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings,
			String wood, Integer numberOfFrets, String colour, String neckWood, String madeIn) {
		super(id, name, brand, type, numberOfStrings, wood, numberOfFrets, colour, neckWood, madeIn);
		this.setGuitarType(GuitarType.CLASSICAL);
	}

	public StringMaterial getStringMaterial() {
		return stringMaterial;
	}

}
