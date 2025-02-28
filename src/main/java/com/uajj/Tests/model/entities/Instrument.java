package com.uajj.Tests.model.entities;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = Guitar.class, name = "GUITAR"),
	@JsonSubTypes.Type(value = Piano.class, name = "PIANO")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instrument implements Serializable {
	private static final long serialVersionUID = 2275576575417265514L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String brand;

	@JsonIgnore //Because JsonTypeInfo will already generate this in the JSON, so not ignoring this would make two of the same "type" fields.
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
	
	public static Instrument fromInstrumentType(InstrumentType type) {
		
		switch(type) {
		case KEYS:
			return new Piano();
		case STRINGED:
			return new Guitar();
		case BRASS:
			//TODO return new BrassInstrument
		case ELECTRONIC:
			//TODO return new ElectronicInstrument
		case PERCUSSION:
			//TODO return new PercussionInstrument
		case WOODWIND:
			//TODO return new WoodwindInstrument
		default:
			throw new IllegalArgumentException("Provided InstrumentType does not exist");
			
		}
		
	}

}
