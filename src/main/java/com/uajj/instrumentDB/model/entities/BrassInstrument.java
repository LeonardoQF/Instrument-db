package com.uajj.instrumentDB.model.entities;

import java.util.UUID;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class BrassInstrument extends Instrument {

	private static final long serialVersionUID = 497937805037996519L;

    private String standardKey;
	private Double boreSize;
	private String valveType;
	private Integer numberOfValves;
	private Double mouthPieceSize;
    private Boolean hasSpitValve;

    public BrassInstrument(UUID id, String name, String brand, Integer releaseYear, String range, InstrumentType type, String standardKey, Double boreSize, String valveType, Integer numberOfValves, Double mouthPieceSize, Boolean hasSpitValve) {
        super(id, name, brand, releaseYear, range, type);
        this.standardKey = standardKey;
        this.boreSize = boreSize;
        this.valveType = valveType;
        this.numberOfValves = numberOfValves;
        this.mouthPieceSize = mouthPieceSize;
        this.hasSpitValve = hasSpitValve;
    }
}
