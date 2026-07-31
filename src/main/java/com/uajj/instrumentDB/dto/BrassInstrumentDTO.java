package com.uajj.instrumentDB.dto;

import com.uajj.instrumentDB.model.entities.BrassInstrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import lombok.*;

@Data
@NoArgsConstructor
public class BrassInstrumentDTO extends InstrumentDTO {

    private String standardKey;
    private Double boreSize;
    private String valveType;
    private Integer numberOfValves;
    private Double mouthPieceSize;
    private Boolean hasSpitValve;

    public BrassInstrumentDTO(String name, String brand, Integer releaseYear, InstrumentType type, String range, String standardKey, Double boreSize, String valveType, Integer numberOfValves, Double mouthPieceSize, Boolean hasSpitValve) {
        super(name, brand, releaseYear, type, range);
        this.standardKey = standardKey;
        this.boreSize = boreSize;
        this.valveType = valveType;
        this.numberOfValves = numberOfValves;
        this.mouthPieceSize = mouthPieceSize;
        this.hasSpitValve = hasSpitValve;
    }
}
