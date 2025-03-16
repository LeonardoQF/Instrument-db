package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.ReedType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@JsonTypeName(value = "WOODWIND")
public class WoodwindInstrument extends Instrument {

	private static final long serialVersionUID = 2108257857467604500L;

	@Enumerated(value = EnumType.STRING)
	private ReedType reedType;
	private String keySystem;
	private String range;
	private String bodyMaterial;

	
	
	public WoodwindInstrument() {
	}

	public WoodwindInstrument(UUID id, String name, String brand, InstrumentType type, ReedType reedType,
			String keySystem, String range, String bodyMaterial) {
		super(id, name, brand, type);
		this.reedType = reedType;
		this.keySystem = keySystem;
		this.range = range;
		this.bodyMaterial = bodyMaterial;
	}

	public ReedType getReedType() {
		return reedType;
	}

	public void setReedType(ReedType reedType) {
		this.reedType = reedType;
	}

	public String getKeySystem() {
		return keySystem;
	}

	public void setKeySystem(String keySystem) {
		this.keySystem = keySystem;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getBodyMaterial() {
		return bodyMaterial;
	}

	public void setBodyMaterial(String bodyMaterial) {
		this.bodyMaterial = bodyMaterial;
	}

}
