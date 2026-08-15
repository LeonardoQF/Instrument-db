package com.uajj.instrumentDB.dto.response;

import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.PercussionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PercussionInstrumentResponseDTO extends InstrumentResponseDTO {

    private PercussionType percussionType;
    private String shellMaterial;
    private String drumHeadMaterial;
    private Boolean tunable;


    public PercussionInstrumentResponseDTO(UUID id, String name, String brand, Integer releaseYear, InstrumentType type, String range, PercussionType percussionType, String shellMaterial, String drumHeadMaterial, Boolean tunable) {
        super(id, name, brand, releaseYear, type, range);
        this.percussionType = percussionType;
        this.shellMaterial = shellMaterial;
        this.drumHeadMaterial = drumHeadMaterial;
        this.tunable = tunable;
    }
}
