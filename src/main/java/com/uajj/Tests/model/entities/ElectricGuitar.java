package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.GuitarType;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;

import jakarta.persistence.Entity;

@Entity
public class ElectricGuitar extends Guitar {

	private static final long serialVersionUID = 4530377743915785269L;
	private final StringMaterial stringMaterial = StringMaterial.STEEL;

	private String pickups; //TODO Create Pickup class
	private Boolean hasWhammyBar;
	private String knobs;
	private String tuningPegMaterial;
	private String bodyShape;

	public ElectricGuitar() {

	}

	public ElectricGuitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings, String wood,
			Integer numberOfFrets, String colour, String neckWood, String madeIn, String pickups, Boolean hasWhammyBar,
			String knobs, String tuningPegMaterial, String bodyShape) {
		super(id, name, brand, type, numberOfStrings, wood, numberOfFrets, colour, neckWood, madeIn);
		this.pickups = pickups;
		this.hasWhammyBar = hasWhammyBar;
		this.knobs = knobs;
		this.tuningPegMaterial = tuningPegMaterial;
		this.bodyShape = bodyShape;
		
		this.setGuitarType(GuitarType.ELECTRIC);
	}

	public String getPickups() {
		return pickups;
	}

	public void setPickups(String pickups) {
		this.pickups = pickups;
	}

	public Boolean getHasWhammyBar() {
		return hasWhammyBar;
	}

	public void setHasWhammyBar(Boolean hasWhammyBar) {
		this.hasWhammyBar = hasWhammyBar;
	}

	public String getKnobs() {
		return knobs;
	}

	public void setKnobs(String knobs) {
		this.knobs = knobs;
	}

	public String getTuningPegMaterial() {
		return tuningPegMaterial;
	}

	public void setTuningPegMaterial(String tuningPegMaterial) {
		this.tuningPegMaterial = tuningPegMaterial;
	}

	public String getBodyShape() {
		return bodyShape;
	}

	public void setBodyShape(String bodyShape) {
		this.bodyShape = bodyShape;
	}

	public StringMaterial getStringMaterial() {
		return this.stringMaterial;
	}

}
