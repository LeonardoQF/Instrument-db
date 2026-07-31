package com.uajj.instrumentDB.mapper;

import com.uajj.instrumentDB.dto.*;
import com.uajj.instrumentDB.dto.response.*;
import com.uajj.instrumentDB.model.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface InstrumentMapper {

    //https://mapstruct.org/documentation/stable/reference/html/#sub-class-mappings

    @SubclassMapping(source = BrassInstrumentDTO.class, target = BrassInstrument.class)
    @SubclassMapping(source = KeysInstrumentDTO.class, target = KeysInstrument.class)
    @SubclassMapping(source = PercussionInstrumentDTO.class, target = PercussionInstrument.class)
    @SubclassMapping(source = StringInstrumentDTO.class, target = StringInstrument.class)
    @SubclassMapping(source = WoodwindInstrumentDTO.class, target = WoodwindInstrument.class)
    Instrument toEntity(InstrumentDTO dto);

    @SubclassMapping(source = BrassInstrument.class, target = BrassInstrumentDTO.class)
    @SubclassMapping(source = KeysInstrument.class, target = KeysInstrumentDTO.class)
    @SubclassMapping(source = PercussionInstrument.class, target = PercussionInstrumentDTO.class)
    @SubclassMapping(source = StringInstrument.class, target = StringInstrumentDTO.class)
    @SubclassMapping(source = WoodwindInstrument.class, target = WoodwindInstrumentDTO.class)
    InstrumentDTO toDto(Instrument entity);

    @SubclassMapping(source = BrassInstrument.class, target = BrassInstrumentResponseDTO.class)
    @SubclassMapping(source = KeysInstrument.class, target = KeysInstrumentResponseDTO.class)
    @SubclassMapping(source = PercussionInstrument.class, target = PercussionInstrumentResponseDTO.class)
    @SubclassMapping(source = StringInstrument.class, target = StringInstrumentResponseDTO.class)
    @SubclassMapping(source = WoodwindInstrument.class, target = WoodwindInstrumentResponseDTO.class)
    InstrumentResponseDTO toResponseDto(Instrument entity);
}
