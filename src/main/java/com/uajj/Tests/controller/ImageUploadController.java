package com.uajj.Tests.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.InstrumentRegistry;
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
