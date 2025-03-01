package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.GuitarType;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("GUITAR")
public class Guitar extends StringInstrument {
	private static final long serialVersionUID = 5023926121064137823L;

	@Enumerated(EnumType.STRING)
	private GuitarType guitarType;
	private Integer numberOfFrets;
	private String neckWood;
	private Boolean hasWhammyBar;
	private Boolean hasBuiltInTuner;
	private String pickups;
	private String bodyShape;

	public Guitar() {
	}

	public Guitar(UUID id, String name, String brand, InstrumentType type, Integer numberOfStrings, String wood,
			StringMaterial stringMaterial, GuitarType guitarType, Integer numberOfFrets, String neckWood,
			Boolean hasWhammyBar, Boolean hasBuiltInTuner, String pickups, String bodyShape) {
		super(id, name, brand, type, numberOfStrings, wood, stringMaterial);
		this.guitarType = guitarType;
		this.numberOfFrets = numberOfFrets;
		this.neckWood = neckWood;
		this.hasWhammyBar = hasWhammyBar;
		this.hasBuiltInTuner = hasBuiltInTuner;
		this.pickups = pickups;
		this.bodyShape = bodyShape;
	}

	public GuitarType getGuitarType() {
		return guitarType;
	}

	public void setGuitarType(GuitarType guitarType) {
		this.guitarType = guitarType;
	}

	public Integer getNumberOfFrets() {
		return numberOfFrets;
	}

	public void setNumberOfFrets(Integer numberOfFrets) {
		this.numberOfFrets = numberOfFrets;
	}

	public String getNeckWood() {
		return neckWood;
	}

	public void setNeckWood(String neckWood) {
		this.neckWood = neckWood;
	}

	public Boolean getHasWhammyBar() {
		return hasWhammyBar;
	}

	public void setHasWhammyBar(Boolean hasWhammyBar) {
		this.hasWhammyBar = hasWhammyBar;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
