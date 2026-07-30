package com.uajj.instrumentDB.model.entities;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.exceptions.NoSuchInstrumentTypeException;

import jakarta.persistence.Column;
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

//TODO instrument subtypes instantiation

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = StringInstrument.class, name = "STRING"),
		@JsonSubTypes.Type(value = KeysInstrument.class, name = "KEYS"),
		@JsonSubTypes.Type(value = PercussionInstrument.class, name = "PERCUSSION"),
		@JsonSubTypes.Type(value = WoodwindInstrument.class, name = "WOODWIND"),
		@JsonSubTypes.Type(value = BrassInstrument.class, name = "BRASS") })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Instrument implements Serializable {
	private static final long serialVersionUID = 2275576575417265514L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String brand;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
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

        return switch (type) {
            case KEYS -> new KeysInstrument();
            case STRING -> new StringInstrument();
            case BRASS -> new BrassInstrument();
            // TODO return new OtherInstrument case OTHER -> new OtherInstrument();
            case PERCUSSION -> new PercussionInstrument();
            case WOODWIND -> new WoodwindInstrument();
            default -> throw new NoSuchInstrumentTypeException("Provided InstrumentType does not exist");
        };
	}

	public static void isValidInstrumentType(String type) {
		try {
			InstrumentType.valueOf(type);
		}catch(IllegalArgumentException e) {
			throw new NoSuchInstrumentTypeException("Provided InstrumentType does not exist: " + type.toString());
		}
	}

}
