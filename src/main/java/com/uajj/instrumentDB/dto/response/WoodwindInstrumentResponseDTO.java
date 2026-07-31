package com.uajj.instrumentDB.dto.response;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.ReedType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class WoodwindInstrumentResponseDTO extends InstrumentResponseDTO {

    private String standardKey;
    private Boolean hasTranspose;
    private ReedType reedType;
    private String bodyMaterial;


    public WoodwindInstrumentResponseDTO(UUID id, String name, String brand, Integer releaseYear, InstrumentType type, String range, String standardKey, Boolean hasTranspose, ReedType reedType, String bodyMaterial) {
        super(id, name, brand, releaseYear, type, range);
        this.standardKey = standardKey;
        this.hasTranspose = hasTranspose;
        this.reedType = reedType;
        this.bodyMaterial = bodyMaterial;
    }
}
