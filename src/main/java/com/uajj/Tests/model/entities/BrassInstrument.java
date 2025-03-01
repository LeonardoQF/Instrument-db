package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;

@Entity
public class BrassInstrument extends Instrument {

	private static final long serialVersionUID = 497937805037996519L;
	
	private Double boreSize;
	private String valveType;
	private Double bellDiameter;
	private Double mouthPieceSize;
	
	public BrassInstrument() {
		
	}

	public BrassInstrument(UUID id, String name, String brand, InstrumentType type, Double boreSize, String valveType,
			Double bellDiameter, Double mouthPieceSize) {
		super(id, name, brand, type);
		this.boreSize = boreSize;
		this.valveType = valveType;
		this.bellDiameter = bellDiameter;
		this.mouthPieceSize = mouthPieceSize;
	}

	public Double getBoreSize() {
		return boreSize;
	}

	public void setBoreSize(Double boreSize) {
		this.boreSize = boreSize;
	}

	public String getValveType() {
		return valveType;
	}

	public void setValveType(String valveType) {
		this.valveType = valveType;
	}

	public Double getBellDiameter() {
		return bellDiameter;
	}

	public void setBellDiameter(Double bellDiameter) {
		this.bellDiameter = bellDiameter;
	}

	public Double getMouthPieceSize() {
		return mouthPieceSize;
	}

	public void setMouthPieceSize(Double mouthPieceSize) {
		this.mouthPieceSize = mouthPieceSize;
	}
	
	
	
	

}
