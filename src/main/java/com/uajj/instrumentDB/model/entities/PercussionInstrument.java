package com.uajj.instrumentDB.model.entities;

import java.util.UUID;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.PercussionType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
//@DiscriminatorValue("PERCUSSION")
@Data
@NoArgsConstructor
public class PercussionInstrument extends Instrument {

	private static final long serialVersionUID = 4556483277105293323L;
	
	private PercussionType percussionType;
	private String shellMaterial;
	private String drumHeadMaterial;
    private Boolean tunable;


    public PercussionInstrument(UUID id, String name, String brand, Integer releaseYear, String range, InstrumentType type, PercussionType percussionType, String shellMaterial, String drumHeadMaterial, Boolean tunable) {
        super(id, name, brand, releaseYear, range, type);
        this.percussionType = percussionType;
        this.shellMaterial = shellMaterial;
        this.drumHeadMaterial = drumHeadMaterial;
        this.tunable = tunable;
    }
}
