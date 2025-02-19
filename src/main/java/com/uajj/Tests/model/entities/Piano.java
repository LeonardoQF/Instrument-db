package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.PianoType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@JsonTypeName("PIANO")
public class Piano extends Instrument {
	private static final long serialVersionUID = 2783973962458540615L;

	private Integer numberOfKeys;
	@Enumerated(EnumType.STRING)
	private PianoType pianoType;

	public Piano() {
	}

	public Piano(UUID id, String name, String brand, InstrumentType type, Integer numberOfKeys, PianoType pianoType) {
		super(id, name, brand, type);
		this.numberOfKeys = numberOfKeys;
		this.pianoType = pianoType;
	}

	public Integer getNumberOfKeys() {
		return numberOfKeys;
	}

	public void setNumberOfKeys(Integer numberOfKeys) {
		this.numberOfKeys = numberOfKeys;
	}

	public PianoType getPianoType() {
		return pianoType;
	}

	public void setPianoType(PianoType type) {
		this.pianoType = type;
	}

	@Override
	public String toString() {
		return "PIANO[" + "id = " + getId() + ", type = " + getType() + ", name = " + getName() + ", brand = "
				+ getBrand() + ", number of keys = " + getNumberOfKeys() + ", piano type = " + getPianoType() + "]";
	}

}
