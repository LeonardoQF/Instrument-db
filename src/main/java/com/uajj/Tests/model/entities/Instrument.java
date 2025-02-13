package com.uajj.Tests.model.entities;

import java.util.UUID;

import com.uajj.Tests.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.ToString;

@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String brand;

	@Enumerated(EnumType.STRING)
	private InstrumentType type;

	public Instrument() {
	}

	public Instrument(UUID id, String name, String brand, InstrumentType type) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public InstrumentType getType() {
		return type;
	}

	public void setType(InstrumentType type) {
		this.type = type;
	}

}
