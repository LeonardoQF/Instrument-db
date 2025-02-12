package com.uajj.Tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uajj.Tests.model.entities.ClassicalGuitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.StringMaterial;
import com.uajj.Tests.service.InstrumentService;

@Component
public class Seeding implements ApplicationRunner {

	private InstrumentService service;

	@Autowired
	public Seeding(InstrumentService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ClassicalGuitar g1 = new ClassicalGuitar(null, "Performance pro", "Giannini", InstrumentType.STRING, 6,
				"Mahogany", StringMaterial.STEEL);
		
		service.save(g1);
		
		Instrument savedGuitar = service.findById(g1.getId());
		
		System.out.println(savedGuitar);
	}

}
