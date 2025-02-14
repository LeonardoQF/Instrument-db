package com.uajj.Tests.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uajj.Tests.model.entities.Guitar;
import com.uajj.Tests.repository.GuitarRepository;

import jakarta.transaction.Transactional;

@Service
public class GuitarService {

	private GuitarRepository repository;

	public GuitarService(GuitarRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Guitar save(Guitar guitar) {
		if (guitar == null)
			throw new IllegalArgumentException("Instrument cannot be null");

		return repository.save(guitar);
	}

	@Transactional
	public List<Guitar> saveAll(List<Guitar> guitars) {
		return repository.saveAll(guitars);
	}

	public Guitar findById(UUID id) {
		Guitar foundGuitar = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Guitar with the specified ID not found"));

		return foundGuitar;
	}

}
