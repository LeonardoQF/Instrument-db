package com.uajj.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uajj.Tests.model.entities.ElectricGuitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.service.ImageStorageService;

@SpringBootTest
class TestsApplicationTests {
	
	
	@Autowired
	ImageStorageService storageService;

	@Test
	void contextLoads() {
	}

	@Test
	void folderCreationTest() {
		
		Instrument instrument = new ElectricGuitar(UUID.fromString("644ed5d4-eb68-4927-9ada-3eebaee41776"), "Les Paul Custom", "Gibson", InstrumentType.STRINGED, 6, "Mahogany", 22,
				"Black", "Basswood", "China", "2 factory issue humbuckers", false, "4", "Silver", "Les Paul");

		Path createdFolder = storageService.createFolder(instrument);
		
		assertEquals(createdFolder.getFileName().toString(), instrument.getId().toString());
	}

}
