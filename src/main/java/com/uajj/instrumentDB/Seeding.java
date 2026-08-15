package com.uajj.instrumentDB;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.KeysInstrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.KeyboardFamily;
import com.uajj.instrumentDB.service.InstrumentService;

@Component
public class Seeding implements ApplicationRunner {
	
	private final InstrumentService service;

	public Seeding(InstrumentService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		KeysInstrument p1 = new KeysInstrument(/*null, "Player 3000", "Fritz Dobbert", InstrumentType.KEYS, 88, KeyboardFamily.ACOUSTIC, 8, true, false, "12-TET"*/);

        p1.setName("Player 3000");
        p1.setBrand("Fritz Dobbert");
        p1.setType(InstrumentType.KEYS);
        p1.setNumberOfKeys(88);
        p1.setKeyboardFamily(KeyboardFamily.ACOUSTIC);
        p1.setOctaves(8);
        p1.setHasHammerAction(true);
        p1.setHasWeightedKeys(true);
        p1.setHasPedalSupport(true);
        p1.setReleaseYear(1995);
        p1.setRange("A0-C8");
		
		service.save(p1);
		

		Instrument savedPiano = service.findById(p1.getId());
		
		System.out.println(savedPiano);
	}

}
