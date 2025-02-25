package com.uajj.Tests;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uajj.Tests.model.entities.ElectricGuitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.Piano;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.PianoType;
import com.uajj.Tests.service.InstrumentService;

@Component
public class Seeding implements ApplicationRunner {
	
	private InstrumentService service;

	public Seeding(InstrumentService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Instrument eg1 = new ElectricGuitar(null, "Les Paul Custom", "Gibson", InstrumentType.STRINGED, 6, "Mahogany", 22, "Black", "Basswood", "China", "2 factory issue humbuckers", false, "4", "Silver", "Les Paul");
		
		Instrument p1 = new Piano(null, "Player 3000", "Fritz Dobbert", InstrumentType.KEYS, 88, PianoType.UPRIGHT);
		
		service.save(eg1);
		
		service.save(p1);
		
		Instrument savedGuitar = service.findById(eg1.getId());
		Instrument savedPiano = service.findById(p1.getId());
		
		System.out.println(savedGuitar + "\n" + savedPiano);
	}

}
