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
import com.uajj.instrumentDB.service.InstrumentService;

import java.util.List;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private InstrumentRepository repository;

	@Autowired
	private InstrumentService service;

	@Test
	void instrumentExistsByIdOnAnyTableTest() {

		WoodwindInstrument flute = new WoodwindInstrument();

        flute.setName("YRS23G");
        flute.setStandardKey("C");
        flute.setReedType(ReedType.NONE);
        flute.setHasTranspose(false);
        flute.setBrand("Yamaha");
        flute.setBodyMaterial("ABS Resin");
        flute.setReleaseYear(1999);
        flute.setRange("C5-D7");
        flute.setType(InstrumentType.WOODWIND);


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
