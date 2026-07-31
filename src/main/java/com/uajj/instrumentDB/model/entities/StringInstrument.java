package com.uajj.instrumentDB.model.entities;

import java.util.UUID;

import com.uajj.instrumentDB.model.entities.enums.GuitarType;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.StringMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
public class StringInstrument extends Instrument {

	private static final long serialVersionUID = 8570137552931555093L;

	private Integer numberOfStrings;
	private String bodyWood;
    private String neckWood;
    private String tuning;
    private Boolean acoustic;
    private Boolean Electric;
    private String pickups;
    private String family;

    @Enumerated(value = EnumType.STRING)
	private StringMaterial stringMaterial;


    public StringInstrument(UUID id, String name, String brand, Integer releaseYear, String range, InstrumentType type, Integer numberOfStrings, String bodyWood, String neckWood, String tuning, Boolean acoustic, Boolean electric, String pickups, String family, StringMaterial stringMaterial) {
        super(id, name, brand, releaseYear, range, type);
        this.numberOfStrings = numberOfStrings;
        this.bodyWood = bodyWood;
        this.neckWood = neckWood;
        this.tuning = tuning;
        this.acoustic = acoustic;
        Electric = electric;
        this.pickups = pickups;
        this.family = family;
        this.stringMaterial = stringMaterial;
    }
}
