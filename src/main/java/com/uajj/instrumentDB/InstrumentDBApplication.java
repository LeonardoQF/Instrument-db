package com.uajj.instrumentDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uajj.instrumentDB.service.ImageStorageService;

@SpringBootApplication
public class InstrumentDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentDBApplication.class, args);
	}

	// * Runs the imageStorageService's init method. Command line runner is a
	// functional Interface and requires the args as per its method signature.
	@Bean
	CommandLineRunner init(ImageStorageService imageStorageService) {
		return (args) -> {
			imageStorageService.init();
		};
	}

}
