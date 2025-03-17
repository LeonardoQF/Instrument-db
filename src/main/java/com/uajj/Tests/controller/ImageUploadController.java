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
import com.uajj.Tests.service.InstrumentRegistryService;
import com.uajj.Tests.service.exceptions.NoSuchInstrumentTypeException;
import com.uajj.Tests.service.exceptions.StorageException;

@Controller
@RequestMapping(path = "/image_upload")
public class ImageUploadController {

	private ImageStorageService service;
	private InstrumentRegistryService instrumentRegistryService;

	public ImageUploadController(ImageStorageService service, InstrumentRegistryService instrumentRegistryService) {
		this.service = service;
		this.instrumentRegistryService = instrumentRegistryService;
	}

	@PostMapping
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image, @RequestParam("id") String id,
			@RequestParam("instrumentType") String instrumentType) {
		try {
			Instrument.isValidInstrumentType(instrumentType);
			if (!instrumentRegistryService.existsById((UUID.fromString(id))))
				throw new StorageException("Instrument must be saved before adding images to it");

			Instrument instrument = Instrument.fromInstrumentType(InstrumentType.valueOf(instrumentType));

			instrument.setId(UUID.fromString(id));
			instrument.setType(InstrumentType.valueOf(instrumentType));

			service.storeFile(image, instrument);

			return ResponseEntity.ok("Image uploaded!");
		} catch (IllegalArgumentException e) {
			throw new StorageException(e.getMessage());
		} catch (NoSuchInstrumentTypeException e) {
			throw new NoSuchInstrumentTypeException("Provided InstrumentType does not exist: " + instrumentType.toString());
		}
	}

}
