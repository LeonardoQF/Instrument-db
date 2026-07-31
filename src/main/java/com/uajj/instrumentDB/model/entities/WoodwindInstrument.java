package com.uajj.instrumentDB.model.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.ReedType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class WoodwindInstrument extends Instrument {

	private static final long serialVersionUID = 2108257857467604500L;

    private String standardKey;
    private Boolean hasTranspose;
    private ReedType reedType;
    private String bodyMaterial;

    public WoodwindInstrument(UUID id, String name, String brand, Integer releaseYear, String range, InstrumentType type, String standardKey, Boolean hasTranspose, ReedType reedType, String bodyMaterial) {
        super(id, name, brand, releaseYear, range, type);
        this.standardKey = standardKey;
        this.hasTranspose = hasTranspose;
        this.reedType = reedType;
        this.bodyMaterial = bodyMaterial;
    }
}
