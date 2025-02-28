package com.uajj.Tests.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;
import com.uajj.Tests.service.ImageStorageService;

@Controller
@RequestMapping(path = "/upload_image")
public class ImageUploadController {

	private ImageStorageService service;

	public ImageUploadController(ImageStorageService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image, @RequestParam("id") String id,
			@RequestParam("instrumentType") String instrumentType) {
		
		Instrument instrument = Instrument.fromInstrumentType(InstrumentType.valueOf(instrumentType));
		
		instrument.setId(UUID.fromString(id));
		instrument.setType(InstrumentType.valueOf(instrumentType));
		
		
		service.storeFile(image, instrument);

		return ResponseEntity.ok("Image uploaded!");

	}

}
