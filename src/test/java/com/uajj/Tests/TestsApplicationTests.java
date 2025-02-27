package com.uajj.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.ElectricGuitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.Piano;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.model.entities.enums.PianoType;
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
	
	@Test
	void imageCreationTest() throws IOException {
		
		Path path = Paths.get("D:\\instrument-db_storage\\PutinTatuado.jpeg");
		
		byte[] imageBytes = Files.readAllBytes(path);
		
		MultipartFile file = new MockMultipartFile("PutinTatuado.jpeg", imageBytes);
		
		file.transferTo(Paths.get("D:\\instrument-db_storage\\test_images\\PutinTatuadoPasted.jpeg"));
	}
	
	@Test
	void createImageInCorrectInstrumentFolderTest() throws IOException {
		Instrument instrument = new Piano(UUID.fromString("5952bdb6-c035-438c-970d-18952e561e3d"), "DGX-670", "YAMAHA", InstrumentType.KEYS, 88, PianoType.DIGITAL);
		
		Path imageToSavePath = Paths.get("D:\\instrument-db_storage\\test_images\\DGX-670_image.jpg");
		
		byte[] imageBytes = Files.readAllBytes(imageToSavePath);
		
		System.out.println("Nome do arquivo para salvar:" + imageToSavePath.getFileName().toString());
		
		MultipartFile imageToSave = new MockMultipartFile(imageToSavePath.getFileName().toString(), imageBytes);
		
		
		storageService.storeFile(imageToSave, instrument);
		
		
	}

}
