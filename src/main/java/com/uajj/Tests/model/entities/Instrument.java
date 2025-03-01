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

/**
 * What @JsonTypeInfo and @JsonSubtypes do is transform the JSON into an object
 * based on what the "type" value is. As Instrument is abstract, sending a JSON
 * through to controller would try to instantiate an Instrument object, which is
 * not possible. If the instrument's type is STRING, however, a new
 * StringInstrument object will be instantiated, which will work because
 * StringInstrument is a concrete class.
 */

//TODO fix empty type name upon request

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = StringInstrument.class, name = "STRING"),
		@JsonSubTypes.Type(value = KeysInstrument.class, name = "KEYS"),
		@JsonSubTypes.Type(value = PercussionInstrument.class, name = "PERCUSSION"),
		@JsonSubTypes.Type(value = WoodwindInstrument.class, name = "WOODWIND"),
		@JsonSubTypes.Type(value = BrassInstrument.class, name = "BRASS"),
		@JsonSubTypes.Type(value = Guitar.class, name = "GUITAR")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instrument implements Serializable {
	private static final long serialVersionUID = 2275576575417265514L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String brand;

	@JsonIgnore // Because JsonTypeInfo will already generate this in the JSON, so not ignoring
				// this would make two of the same "type" fields.
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

	public static final Instrument fromInstrumentType(InstrumentType type) {

		switch (type) {
		case KEYS:
			return new KeysInstrument();
		case STRINGS:
			return new Guitar();
		case BRASS:
			// TODO return new BrassInstrument
		case OTHER:
			// TODO return new OtherInstrument
		case PERCUSSION:
			// TODO return new PercussionInstrument
		case WOODWIND:
			// TODO return new WoodwindInstrument
		default:
			throw new IllegalArgumentException("Provided InstrumentType does not exist");

		}

	}

}
