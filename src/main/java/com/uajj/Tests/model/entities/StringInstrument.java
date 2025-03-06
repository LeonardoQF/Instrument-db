package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "")
public class StringInstrument extends Instrument {

	private static final long serialVersionUID = 8570137552931555093L;

	private Integer numberOfStrings;
	private String wood;
	@Enumerated(value = EnumType.STRING)
	private StringMaterial stringMaterial;

	public StringInstrument() {

	}

	public StringInstrument(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings,
			String wood, StringMaterial stringMaterial) {
		super(id, name, brand, type);
		super.setType(InstrumentType.STRING);
		this.numberOfStrings = numberOfStrings;
		this.wood = wood;
		this.stringMaterial = stringMaterial;
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

}
