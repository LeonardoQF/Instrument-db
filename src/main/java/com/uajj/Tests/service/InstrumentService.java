package com.uajj.Tests.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.repository.InstrumentRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class InstrumentService {

	private InstrumentRepository repository;

	public InstrumentService(InstrumentRepository repository) {
		this.repository = repository;
	}

	public Instrument save(Instrument instrument) {
		if (instrument == null)
			throw new IllegalArgumentException("Instrument cannot be null");

		return repository.save(instrument);
	}

	public List<Instrument> saveAll(List<Instrument> instruments) {
		if (instruments == null || instruments.size() == 0)
			throw new IllegalArgumentException("Instrument list cannot be null");

		return repository.saveAll(instruments);
	}

	public Instrument findById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Instrument with the specified ID not found"));
	}

	public List<Instrument> findAll() {
		return repository.findAll();
	}

	public List<Instrument> findAllByType(InstrumentType type) {
		return repository.findByType(type);
	}

	public List<Instrument> searchByAnyText(String text) {
		return repository.searchByAnyText(text);
	}

}
