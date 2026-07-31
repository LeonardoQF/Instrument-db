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
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Instrument implements Serializable {
	private static final long serialVersionUID = 2275576575417265514L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String brand;
    private Integer releaseYear;
    private String range;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private InstrumentType type;

	public static Instrument fromInstrumentType(InstrumentType type) {

        return switch (type) {
            case KEYS -> new KeysInstrument();
            case STRING -> new StringInstrument();
            case BRASS -> new BrassInstrument();
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
