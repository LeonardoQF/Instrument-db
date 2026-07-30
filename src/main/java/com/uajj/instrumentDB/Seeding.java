package com.uajj.instrumentDB;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.KeysInstrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.model.entities.enums.PianoType;
import com.uajj.instrumentDB.service.InstrumentService;

@Component
public class Seeding implements ApplicationRunner {
	
	private final InstrumentService service;

	public Seeding(InstrumentService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		Instrument p1 = new KeysInstrument(null, "Player 3000", "Fritz Dobbert", InstrumentType.KEYS, 88, PianoType.ACOUSTIC, 8, true, false, "12-TET");
		
		service.save(p1);
		

		Instrument savedPiano = service.findById(p1.getId());
		
		System.out.println(savedPiano);
	}

}
