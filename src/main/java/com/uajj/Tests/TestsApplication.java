package com.uajj.Tests;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uajj.Tests.model.entities.ElectricGuitar;
import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.service.ImageStorageService;

@SpringBootApplication
public class TestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestsApplication.class, args);
	}

	// * Runs the imageStorageService's init method. Command line runner is a
	// functional Interface and requires the args as per it's method signature.
	@Bean
	CommandLineRunner init(ImageStorageService imageStorageService) {
		return args -> {
			imageStorageService.init();
		};
	}
	
	@Bean
	CommandLineRunner createFolderTest(ImageStorageService imageStorageService) {
		return args -> {
			Instrument instrument = new ElectricGuitar(UUID.fromString("644ed5d4-eb68-4927-9ada-3eebaee41789"), "Les Paul Custom", "Gibson", InstrumentType.STRINGED, 6, "Mahogany", 22,
					"Black", "Basswood", "China", "2 factory issue humbuckers", false, "4", "Silver", "Les Paul");
			
			System.out.println("Created path: " + imageStorageService.createFolder(instrument));
			
			
		};
	}

}
