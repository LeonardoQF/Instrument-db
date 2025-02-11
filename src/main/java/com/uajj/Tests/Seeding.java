package com.uajj.Tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uajj.Tests.model.entities.ClassicalGuitar;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;
import com.uajj.Tests.repository.ClassicalGuitarRepository;

@Component
public class Seeding implements ApplicationRunner {

	private ClassicalGuitarRepository repository;

	@Autowired
	public Seeding(ClassicalGuitarRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ClassicalGuitar g1 = new ClassicalGuitar(null, "Performance pro", "Giannini", InstrumentType.STRING, 6,
				"Mahogany", StringMaterial.STEEL);
		
		repository.save(g1);
		
		ClassicalGuitar savedGuitar = repository.findById(g1.getId()).get();
		
		System.out.println(savedGuitar);
	}

}
