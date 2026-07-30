package com.uajj.instrumentDB;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.uajj.instrumentDB.repository.InstrumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.WoodwindInstrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.ReedType;
import com.uajj.instrumentDB.service.InstrumentRegistryService;
import com.uajj.instrumentDB.service.InstrumentService;

import java.util.List;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private InstrumentRepository repository;

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

		assertTrue(service.existsById(flute.getId()));
	}

    @Test
    void instrumentRepositoryUnionTest() {
        long loggedInstrumentAmount = repository.count();

        System.out.println("Instruments logged: " + loggedInstrumentAmount);

        List<Instrument> instruments = service.findAll();

        System.out.println(instruments);
    }

}
