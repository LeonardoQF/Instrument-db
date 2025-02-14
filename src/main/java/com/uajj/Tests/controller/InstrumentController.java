package com.uajj.Tests.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.service.InstrumentService;

@RestController
@RequestMapping(path = "/instruments")
public class InstrumentController {
	
	InstrumentService service;
	
	public InstrumentController(InstrumentService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Instrument> getAllInstruments() {
		return service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Instrument getInstrumentById(@PathVariable String id) {
		
		return service.findById(UUID.fromString(id));
	}
	
	@PostMapping
	public Instrument addInstrument(@RequestBody(required = true) Instrument instrument) {
		return service.save(instrument);
	}
	
	@GetMapping("/filter")
	public List<Instrument> getAllInstrumentsByType(@RequestParam(required = false) String type) {
		String upperCaseType = type.toUpperCase();
		return service.findAllByType(InstrumentType.valueOf(upperCaseType));
	}
	
	@GetMapping("/search")
	public List<Instrument> search(@RequestParam(required = true) String text) {
		return service.searchByAnyText(text);
	}
	
	

}
