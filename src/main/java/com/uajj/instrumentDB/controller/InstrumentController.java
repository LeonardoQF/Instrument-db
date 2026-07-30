package com.uajj.instrumentDB.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.InstrumentRegistry;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.InstrumentRegistryService;
import com.uajj.instrumentDB.service.InstrumentService;

@RestController
@RequestMapping(path = "/instruments")
public class InstrumentController {

	private final InstrumentService service;
	private final InstrumentRegistryService instrumentRegistryService;

	public InstrumentController(InstrumentService service, InstrumentRegistryService instrumentRegistryService) {
		this.service = service;
		this.instrumentRegistryService = instrumentRegistryService;
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
	public ResponseEntity<Instrument> addInstrument(@RequestBody(required = true) Instrument instrument) {
		service.save(instrument);
		instrumentRegistryService.save(new InstrumentRegistry(instrument.getId(), instrument.getType())); //Salva o ID do instrumento enviado na tabela InstrumentRegistry
		

		URI uri = (ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(instrument.getId().toString()).toUri());

		return ResponseEntity.created(uri).body(instrument);
	}

	@GetMapping("/filter")
	public List<Instrument> getAllInstrumentsByType(@RequestParam(required = true, name = "type") String type) {
		String upperCaseType = type.toUpperCase();
		return service.findAllByType(InstrumentType.valueOf(upperCaseType));
	}

	@GetMapping("/search")
	public List<Instrument> search(@RequestParam(required = true, name = "text") String text) {
		return service.searchByAnyText(text);
	}

}
