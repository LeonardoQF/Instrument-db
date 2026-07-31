package com.uajj.instrumentDB.dto;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.KeyboardFamily;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class KeysInstrumentDTO extends InstrumentDTO {

    private Integer numberOfKeys;
    private KeyboardFamily keyboardFamily;
    private Integer octaves;
    private Boolean hasHammerAction;
    private Boolean hasWeightedKeys;
    private Boolean hasPedalSupport;

    public KeysInstrumentDTO(String name, String brand, Integer releaseYear, InstrumentType type, String range, Integer numberOfKeys, KeyboardFamily keyboardFamily, Integer octaves, Boolean hasHammerAction, Boolean hasWeightedKeys, Boolean hasPedalSupport) {
        super(name, brand, releaseYear, type, range);
        this.numberOfKeys = numberOfKeys;
        this.keyboardFamily = keyboardFamily;
        this.octaves = octaves;
        this.hasHammerAction = hasHammerAction;
        this.hasWeightedKeys = hasWeightedKeys;
        this.hasPedalSupport = hasPedalSupport;
    }
}
