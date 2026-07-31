package com.uajj.instrumentDB.dto.response;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.KeyboardFamily;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
public class KeysInstrumentResponseDTO extends InstrumentResponseDTO {

    private Integer numberOfKeys;
    private KeyboardFamily keyboardFamily;
    private Integer octaves;
    private Boolean hasHammerAction;
    private Boolean hasWeightedKeys;
    private Boolean hasPedalSupport;

    public KeysInstrumentResponseDTO(UUID id, String name, String brand, Integer releaseYear, InstrumentType type, String range, Integer numberOfKeys, KeyboardFamily keyboardFamily, Integer octaves, Boolean hasHammerAction, Boolean hasWeightedKeys, Boolean hasPedalSupport) {
        super(id, name, brand, releaseYear, type, range);
        this.numberOfKeys = numberOfKeys;
        this.keyboardFamily = keyboardFamily;
        this.octaves = octaves;
        this.hasHammerAction = hasHammerAction;
        this.hasWeightedKeys = hasWeightedKeys;
        this.hasPedalSupport = hasPedalSupport;
    }
}
