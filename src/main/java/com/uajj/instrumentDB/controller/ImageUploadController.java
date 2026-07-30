package com.uajj.instrumentDB.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.InstrumentRegistry;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.ImageStorageService;
import com.uajj.instrumentDB.service.InstrumentRegistryService;
import com.uajj.instrumentDB.service.exceptions.NoSuchInstrumentTypeException;
import com.uajj.instrumentDB.service.exceptions.StorageException;

@RestController
@RequestMapping(path = "/image_upload")
public class ImageUploadController {

	private final ImageStorageService service;
	private final InstrumentRegistryService instrumentRegistryService;

	public ImageUploadController(ImageStorageService service, InstrumentRegistryService instrumentRegistryService) {
		this.service = service;
		this.instrumentRegistryService = instrumentRegistryService;
	}

    //TODO Change HTTP status code from 200 OK to 201 CREATED
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
			throw new NoSuchInstrumentTypeException(
					"Provided InstrumentType does not exist: " + instrumentType.toString());
		}
	}

	@GetMapping
	public ResponseEntity<List<String>> getAllInstrumentImages(@RequestParam String id,
			@RequestParam String instrumentType) {
		try {
			UUID uuid = UUID.fromString(id);
			InstrumentType type = InstrumentType.valueOf(instrumentType);
			List<String> imagesUrls = service.getInstrumentImagesAsUrls(new InstrumentRegistry(uuid, type));

			return ResponseEntity.ok().body(imagesUrls);
		} catch (Exception e) { // temporary
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("image")
	public ResponseEntity<Resource> getInstrumentImage(@RequestParam String id, @RequestParam String instrumentType,
			@RequestParam String filename) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
				service.getAsResource(new InstrumentRegistry(UUID.fromString(id), InstrumentType.valueOf(instrumentType)), filename));
	}

}
