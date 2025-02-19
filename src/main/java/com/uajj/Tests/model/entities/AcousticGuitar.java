package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;

@Entity
public class AcousticGuitar extends Guitar {

	private static final long serialVersionUID = 6588876732999960716L;
	private final String StringMaterial = "Steel";

	private Boolean hasBuiltInTuner;
	private String pickups; // Create a Pickup class later?
	private String bodyShape;

	public AcousticGuitar() {

	}

	public AcousticGuitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings, String wood,
			Integer numberOfFrets, String colour, String neckWood, String madeIn, Boolean hasBuiltInTuner,
			String pickups, String bodyShape) {
		super(id, name, brand, type, numberOfStrings, wood, numberOfFrets, colour, neckWood, madeIn);
		this.hasBuiltInTuner = hasBuiltInTuner;
		this.pickups = pickups;
		this.bodyShape = bodyShape;
	}

	public Boolean getHasBuiltInTuner() {
		return hasBuiltInTuner;
	}

	public void setHasBuiltInTuner(Boolean hasBuiltInTuner) {
		this.hasBuiltInTuner = hasBuiltInTuner;
	}

	public String getPickups() {
		return pickups;
	}

	public void setPickups(String pickups) {
		this.pickups = pickups;
	}

	public String getBodyShape() {
		return bodyShape;
	}

	public void setBodyShape(String bodyShape) {
		this.bodyShape = bodyShape;
	}

	public String getStringMaterial() {
		return StringMaterial;
	}

}
