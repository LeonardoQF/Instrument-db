package com.uajj.Tests.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uajj.Tests.model.entities.InstrumentRegistry;
import com.uajj.Tests.repository.InstrumentRegistryRepository;

@Service
public class InstrumentRegistryService {

	InstrumentRegistryRepository repository;

	public InstrumentRegistryService(InstrumentRegistryRepository repository) {
		this.repository = repository;
	}
	
	public InstrumentRegistry findById(UUID id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Instrument with the specified id does not exist"));
	}

	public boolean existsById(UUID id) {
		return repository.existsById(id);
	}
	
	public InstrumentRegistry save(InstrumentRegistry instrumentRegistry) {
		return repository.save(instrumentRegistry);
	}
	
	
	

}
