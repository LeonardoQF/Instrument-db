package com.uajj.instrumentDB.dto;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.PercussionType;
import lombok.*;

@Data
@NoArgsConstructor
public class PercussionInstrumentDTO extends InstrumentDTO {

    private PercussionType percussionType;
    private String shellMaterial;
    private String drumHeadMaterial;
    private Boolean tunable;

    public PercussionInstrumentDTO(String name, String brand, Integer releaseYear, InstrumentType type, String range, PercussionType percussionType, String shellMaterial, String drumHeadMaterial, Boolean tunable) {
        super(name, brand, releaseYear, type, range);
        this.percussionType = percussionType;
        this.shellMaterial = shellMaterial;
        this.drumHeadMaterial = drumHeadMaterial;
        this.tunable = tunable;
    }
}
