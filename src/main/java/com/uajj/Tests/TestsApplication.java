package com.uajj.Tests;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
