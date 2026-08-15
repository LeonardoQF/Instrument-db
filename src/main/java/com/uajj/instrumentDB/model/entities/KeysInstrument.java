package com.uajj.instrumentDB.model.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.KeyboardFamily;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class KeysInstrument extends Instrument {
    private static final long serialVersionUID = 2783973962458540615L;

	private Integer numberOfKeys;
	@Enumerated(EnumType.STRING)
	private KeyboardFamily keyboardFamily;
	private Integer octaves;
	private Boolean hasHammerAction;
    private Boolean hasWeightedKeys;
	private Boolean hasPedalSupport;

    public KeysInstrument(UUID id, String name, String brand, Integer releaseYear, String range, InstrumentType type, Integer numberOfKeys, KeyboardFamily keyboardFamily, Integer octaves, Boolean hasHammerAction, Boolean hasWeightedKeys, Boolean hasPedalSupport) {
        super(id, name, brand, releaseYear, range, type);
        this.numberOfKeys = numberOfKeys;
        this.keyboardFamily = keyboardFamily;
        this.octaves = octaves;
        this.hasHammerAction = hasHammerAction;
        this.hasWeightedKeys = hasWeightedKeys;
        this.hasPedalSupport = hasPedalSupport;
    }
}
