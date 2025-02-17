package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uajj.Tests.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity
@JsonTypeName(value = "GUITAR")
@Inheritance
public abstract class Guitar extends Instrument {
	private static final long serialVersionUID = 5023926121064137823L;

	private Integer numberOfStrings;
	private String wood;
	private Integer numberOfFrets;
	private String colour;
	private String neckWood;
	private String madeIn;

	public Guitar() {
	}
	public Guitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings, String wood,
			Integer numberOfFrets, String colour, String neckWood, String madeIn) {
		super(id, name, brand, type);
		this.numberOfStrings = numberOfStrings;
		this.wood = wood;
		this.numberOfFrets = numberOfFrets;
		this.colour = colour;
		this.neckWood = neckWood;
		this.madeIn = madeIn;
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

	public Integer getNumberOfFrets() {
		return numberOfFrets;
	}

	public void setNumberOfFrets(Integer numberOfFrets) {
		this.numberOfFrets = numberOfFrets;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getNeckWood() {
		return neckWood;
	}

	public void setNeckWood(String neckWood) {
		this.neckWood = neckWood;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	@Override
	public String toString() {
		return "ClassicalGuitar [NumberOfStrings()=" + getNumberOfStrings() + ", Wood=" + getWood()
				+ ", id=" + getId() + ", name=" + getName() + ", brand="
				+ getBrand() + ", type=" + getType() + "]";
	}

}
