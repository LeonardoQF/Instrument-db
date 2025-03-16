package com.uajj.Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.WoodwindInstrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.ReedType;
import com.uajj.Tests.service.InstrumentRegistryService;
import com.uajj.Tests.service.InstrumentService;

@SpringBootTest
public class DatabaseTests {

	@Autowired
	private InstrumentService service;
	
	@Autowired
	private InstrumentRegistryService instrumentRegistryService;

	@Test
	void instrumentExistsByIdOnAnyTableTest() {

		Instrument flute = new WoodwindInstrument(null, "Concert Flute", "Yamaha", InstrumentType.WOODWIND,
				ReedType.NONE, "Boehm System", "C4 to C7", "Silver-plated nickel silver");

		service.save(flute);
		
		System.out.println("ID DA FLAUTA: " + flute.getId().toString());

		assertTrue(instrumentRegistryService.existsById(flute.getId()));
	}

}
