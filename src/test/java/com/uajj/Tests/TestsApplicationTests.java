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

import com.uajj.Tests.model.entities.Guitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.KeysInstrument;
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

		/*Instrument instrument = new Guitar(UUID.fromString("84d11cc7-7e15-4b49-9e25-b2a319f15090"),
				"Performance GGC plus", "Giannini", InstrumentType.STRINGS, 6, GuitarType.ACOUSTIC ,"Mahogany", StringMaterial.STEEL , 24, false, "Red Satin", "Oak",
				"China");*/
		
		Instrument instrument = new Guitar();
				

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
		Instrument instrument = new KeysInstrument(UUID.fromString("5952bdb6-c035-438c-970d-18952e561e3d"), "DGX-670", "YAMAHA",
				InstrumentType.KEYS, 88, PianoType.DIGITAL);

		Path imageToSavePath = Paths.get("D:\\instrument-db_storage\\test_images\\DGX-670_image.jpg");

		byte[] imageBytes = Files.readAllBytes(imageToSavePath);

		System.out.println("Nome do arquivo para salvar:" + imageToSavePath.getFileName().toString());

		MultipartFile imageToSave = new MockMultipartFile(imageToSavePath.getFileName().toString(), imageBytes);

		storageService.storeFile(imageToSave, instrument);

	}

}
