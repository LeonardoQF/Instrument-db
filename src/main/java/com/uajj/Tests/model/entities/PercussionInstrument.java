package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.PercussionType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PERCUSSION")
public class PercussionInstrument extends Instrument {

	private static final long serialVersionUID = 4556483277105293323L;
	
	private PercussionType percussionType;
	private String material;
	private String drumheadMaterial;
	
	
	public PercussionInstrument() {
		
	}


	public PercussionInstrument(UUID id, String name, String brand, InstrumentType type, PercussionType percussionType,
			String material, String drumheadMaterial) {
		super(id, name, brand, type);
		this.percussionType = percussionType;
		this.material = material;
		this.drumheadMaterial = drumheadMaterial;
	}


	public PercussionType getPercussionType() {
		return percussionType;
	}


	public void setPercussionType(PercussionType percussionType) {
		this.percussionType = percussionType;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public String getDrumheadMaterial() {
		return drumheadMaterial;
	}


	public void setDrumheadMaterial(String drumheadMaterial) {
		this.drumheadMaterial = drumheadMaterial;
	}
	
	
	 
	
}
