package com.uajj.instrumentDB;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.uajj.instrumentDB.model.entities.StringInstrument;
import com.uajj.instrumentDB.model.entities.enums.KeyboardFamily;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.KeysInstrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.ImageStorageService;
import com.uajj.instrumentDB.service.InstrumentService;

@SpringBootTest
class TestsApplicationTests {

	@Autowired
	ImageStorageService storageService;
	
	@Autowired
	InstrumentService service;

	@Test
	void contextLoads() {
	}

	@Test
	void folderCreationTest() {

		/*Instrument instrument = new Guitar(UUID.fromString("84d11cc7-7e15-4b49-9e25-b2a319f15090"),
				"Performance GGC plus", "Giannini", InstrumentType.STRINGS, 6, GuitarType.ACOUSTIC ,"Mahogany", StringMaterial.STEEL , 24, false, "Red Satin", "Oak",
				"China");*/
		
		Instrument instrument = new StringInstrument();
				

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
		Instrument instrument = new KeysInstrument(UUID.fromString("5952bdb6-c035-438c-970d-18952e561e3d"), "DGX-670", "YAMAHA", 2021, "A0-C8", InstrumentType.KEYS, 88, KeyboardFamily.DIGITAL, 8, false, true, true);

		Path imageToSavePath = Paths.get("D:\\instrument-db_storage\\test_images\\DGX-670_image.jpg");

		byte[] imageBytes = Files.readAllBytes(imageToSavePath);

		System.out.println("Nome do arquivo para salvar:" + imageToSavePath.getFileName().toString());

		MultipartFile imageToSave = new MockMultipartFile(imageToSavePath.getFileName().toString(), imageBytes);

		storageService.storeFile(imageToSave, instrument);

	}
}
