package com.uajj.instrumentDB.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.uajj.instrumentDB.dto.InstrumentDTO;
import com.uajj.instrumentDB.dto.response.InstrumentResponseDTO;
import com.uajj.instrumentDB.mapper.InstrumentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.uajj.instrumentDB.controller.GenericController.generateURI;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.InstrumentService;

@RestController
@RequestMapping(path = "/instruments")
public class InstrumentController implements GenericController {

	private final InstrumentService service;
    private final InstrumentMapper mapper;

	public InstrumentController(InstrumentService service, InstrumentMapper mapper) {
		this.service = service;
        this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<Instrument>> getAllInstruments() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Instrument> getInstrumentById(@PathVariable(name = "id") String id) {
		Instrument foundInstrument = service.findById(UUID.fromString(id));
		return ResponseEntity.ok(foundInstrument);
	}
	
	@PostMapping
	public ResponseEntity<InstrumentResponseDTO> addInstrument(@RequestBody(required = true) InstrumentDTO instrumentDto) {

        Instrument savedInstrument = service.save(mapper.toEntity(instrumentDto));

		URI uri = generateURI(savedInstrument.getId().toString());

        //Return a ResponseDTO
		return ResponseEntity.created(uri).body(mapper.toResponseDto(savedInstrument));
	}

	@GetMapping("/filter")
	public ResponseEntity<List<InstrumentResponseDTO>> getAllInstrumentsByType(@RequestParam(required = true, name = "type") String type) {
		String upperCaseType = type.toUpperCase();

        List<InstrumentResponseDTO> instrumentsDto = service.findAllByType(InstrumentType.valueOf(upperCaseType)).stream().map(mapper::toResponseDto).toList();

		return ResponseEntity.ok().body(instrumentsDto);
	}

	@GetMapping("/search")
	public ResponseEntity<List<InstrumentResponseDTO>> search(@RequestParam(required = true, name = "text") String text) {
		List<InstrumentResponseDTO> instrumentsDto = service.searchByAnyText(text).stream().map(mapper::toResponseDto).toList();

        return ResponseEntity.ok().body(instrumentsDto);
	}

}
