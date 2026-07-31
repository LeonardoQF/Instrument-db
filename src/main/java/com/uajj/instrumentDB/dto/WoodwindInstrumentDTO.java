package com.uajj.instrumentDB.dto;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.ReedType;
import lombok.*;

@Data
@NoArgsConstructor
public class WoodwindInstrumentDTO extends InstrumentDTO {

    private String key;
    private Boolean hasTranspose;
    private ReedType reedType;
    private String bodyMaterial;

    public WoodwindInstrumentDTO(String name, String brand, Integer releaseYear, InstrumentType type, String range, String key, Boolean hasTranspose, ReedType reedType, String bodyMaterial) {
        super(name, brand, releaseYear, type, range);
        this.key = key;
        this.hasTranspose = hasTranspose;
        this.reedType = reedType;
        this.bodyMaterial = bodyMaterial;
    }
}
