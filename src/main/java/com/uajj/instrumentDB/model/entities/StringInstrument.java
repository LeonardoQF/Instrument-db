package com.uajj.instrumentDB.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.uajj.instrumentDB.model.entities.enums.GuitarType;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.StringMaterial;
import com.uajj.instrumentDB.util.StringInstrumentDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@JsonDeserialize(using = StringInstrumentDeserializer.class)
public class StringInstrument extends Instrument {

	private static final long serialVersionUID = 8570137552931555093L;

	private Integer numberOfStrings;
	private String wood;
	@Enumerated(value = EnumType.STRING)
	private StringMaterial stringMaterial;
	@Enumerated(EnumType.STRING)
	private GuitarType guitarType;

	public StringInstrument() {

	}

	public StringInstrument(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings,
			String wood, StringMaterial stringMaterial, GuitarType guitarType) {
		super(id, name, brand, type);
		this.numberOfStrings = numberOfStrings;
		this.wood = wood;
		this.stringMaterial = stringMaterial;
		this.guitarType = guitarType;
	}

	public Integer getNumberOfStrings() {
		return numberOfStrings;
	}

	public void setNumberOfStrings(Integer numberOfStrings) {
		this.numberOfStrings = numberOfStrings;
	}

	public String getWood() {
		return wood;
	}

	public void setWood(String wood) {
		this.wood = wood;
	}

	public StringMaterial getStringMaterial() {
		return stringMaterial;
	}

	public void setStringMaterial(StringMaterial stringMaterial) {
		this.stringMaterial = stringMaterial;
	}

	public GuitarType getGuitarType() {
		return guitarType;
	}

	public void setGuitarType(GuitarType guitarType) {
		this.guitarType = guitarType;
	}

}
