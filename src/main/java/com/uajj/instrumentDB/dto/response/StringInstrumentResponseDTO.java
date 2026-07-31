package com.uajj.instrumentDB.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.uajj.instrumentDB.model.entities.enums.GuitarType;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.StringMaterial;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StringInstrumentResponseDTO extends InstrumentResponseDTO {

    private Integer numberOfStrings;
    private String bodyWood;
    private String neckWood;
    private String tuning;
    private Boolean acoustic;
    private Boolean Electric;
    private String pickups;
    private String family;
    private StringMaterial stringMaterial;

    public StringInstrumentResponseDTO(UUID id, String name, String brand, Integer releaseYear, InstrumentType type, String range, Integer numberOfStrings, String bodyWood, String neckWood, String tuning, Boolean acoustic, Boolean electric, String pickups, String family, StringMaterial stringMaterial) {
        super(id, name, brand, releaseYear, type, range);
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
