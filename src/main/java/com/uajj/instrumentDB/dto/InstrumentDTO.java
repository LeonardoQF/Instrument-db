package com.uajj.instrumentDB.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.uajj.instrumentDB.model.entities.*;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


/**
 * What @JsonTypeInfo and @JsonSubtypes do is transform the JSON into an object
 * based on what the "type" value is. As Instrument is abstract, sending a JSON
 * through to controller would try to instantiate an Instrument object, which is
 * not possible. If the instrument's type is STRING, however, a new
 * StringInstrument object will be instantiated, which will work because
 * StringInstrument is a concrete class.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = StringInstrumentDTO.class, name = "STRING"),
        @JsonSubTypes.Type(value = KeysInstrumentDTO.class, name = "KEYS"),
        @JsonSubTypes.Type(value = PercussionInstrumentDTO.class, name = "PERCUSSION"),
        @JsonSubTypes.Type(value = WoodwindInstrumentDTO.class, name = "WOODWIND"),
        @JsonSubTypes.Type(value = BrassInstrumentDTO.class, name = "BRASS") })
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class InstrumentDTO {

    private String name;
    private String brand;
    private Integer releaseYear;
    private InstrumentType type;
    private String range;



}
