package com.uajj.Tests.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.service.InstrumentService;

@RestController
@RequestMapping(path = "/instrument")
public class InstrumentController {
	
	InstrumentService service;
	
	@Autowired
	public InstrumentController(InstrumentService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}")
	public Instrument getInstrumentById(@PathVariable String id) {
		
		return service.findById(UUID.fromString(id));
	}
	
	

}
