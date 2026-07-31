package com.uajj.instrumentDB.dto.response;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class BrassInstrumentResponseDTO extends InstrumentResponseDTO {

    private String standardKey;
    private Double boreSize;
    private String valveType;
    private Integer numberOfValves;
    private Double mouthPieceSize;
    private Boolean hasSpitValve;

    public BrassInstrumentResponseDTO(UUID id, String name, String brand, Integer releaseYear, InstrumentType type, String range, String standardKey, Double boreSize, String valveType, Integer numberOfValves, Double mouthPieceSize, Boolean hasSpitValve) {
        super(id, name, brand, releaseYear, type, range);
        this.standardKey = standardKey;
        this.boreSize = boreSize;
        this.valveType = valveType;
        this.numberOfValves = numberOfValves;
        this.mouthPieceSize = mouthPieceSize;
        this.hasSpitValve = hasSpitValve;
    }
}
