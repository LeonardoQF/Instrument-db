package com.uajj.Tests.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uajj.Tests.model.entities.ClassicalGuitar;
import com.uajj.Tests.repository.ClassicalGuitarRepository;

import jakarta.transaction.Transactional;

@Service
public class ClassicalGuitarService {

	private ClassicalGuitarRepository repository;

	@Autowired
	public ClassicalGuitarService(ClassicalGuitarRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public ClassicalGuitar save(ClassicalGuitar guitar) {
		if (guitar == null)
			throw new IllegalArgumentException("Instrument cannot be null");

		return repository.save(guitar);
	}

	@Transactional
	public List<ClassicalGuitar> saveAll(List<ClassicalGuitar> guitars) {
		return repository.saveAll(guitars);
	}

	public ClassicalGuitar findById(UUID id) {
		ClassicalGuitar foundGuitar = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Guitar with the specified ID not found"));

		return foundGuitar;
	}

}
