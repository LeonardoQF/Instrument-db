package com.uajj.instrumentDB.model.entities;

import java.io.Serializable;
import java.util.UUID;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

/**
 * Class representing a table which will have the ID of all present instrument
 * fields. It is used to: <br>
 * - Count how many instruments exist on the database; <br>
 * - Check if an instrument exists based on their ID.
 */

@Entity
public class InstrumentRegistry implements Serializable {

	private static final long serialVersionUID = 5937901044934053343L;

	@Id
	private UUID id;

	@Enumerated(EnumType.STRING)
	private InstrumentType instrumentType;

	public InstrumentRegistry() {

	}

	public InstrumentRegistry(UUID id) {
		this.id = id;
	}

	public InstrumentRegistry(UUID id, InstrumentType instrumentType) {
		this.id = id;
		this.instrumentType = instrumentType;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public InstrumentType getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(InstrumentType instrumentType) {
		this.instrumentType = instrumentType;
	}

}
