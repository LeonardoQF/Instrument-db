package com.uajj.instrumentDB.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.uajj.instrumentDB.service.InstrumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.uajj.instrumentDB.controller.GenericController.generateURI;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.enums.InstrumentType;
import com.uajj.instrumentDB.service.ImageStorageService;
import com.uajj.instrumentDB.service.exceptions.NoSuchInstrumentTypeException;
import com.uajj.instrumentDB.service.exceptions.StorageException;

@RestController
@RequestMapping(path = "/image_upload")
public class ImageUploadController implements GenericController {

    private final ImageStorageService service;
	private final InstrumentService instrumentService;

	public ImageUploadController(ImageStorageService service, InstrumentService instrumentService) {
		this.service = service;
		this.instrumentService = instrumentService;
	}

	@PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image, @RequestParam("id") String id,
			@RequestParam("instrumentType") String instrumentType) {
		try {
			Instrument.isValidInstrumentType(instrumentType);
			if (!instrumentService.existsById((UUID.fromString(id))))
				throw new StorageException("Instrument must be saved before adding images to it");

			Instrument instrument = Instrument.fromInstrumentType(InstrumentType.valueOf(instrumentType));

			instrument.setId(UUID.fromString(id));
			instrument.setType(InstrumentType.valueOf(instrumentType));

			service.storeFile(image, instrument);

            URI uri = generateURI(instrument.getId().toString());

			return ResponseEntity.created(uri).body("Image uploaded successfully!");
		} catch (IllegalArgumentException e) {
			throw new StorageException(e.getMessage());
		} catch (NoSuchInstrumentTypeException e) {
			throw new NoSuchInstrumentTypeException(
					"Provided InstrumentType does not exist: " + instrumentType.toString());
		}
	}

	@GetMapping
	public ResponseEntity<List<String>> getAllInstrumentImagesURLs(@RequestParam String id,
			@RequestParam String instrumentType) {
		try {
			UUID uuid = UUID.fromString(id);
			InstrumentType type = InstrumentType.valueOf(instrumentType);

            Instrument instrumentForLookup = Instrument.fromInstrumentType(type);

            instrumentForLookup.setId(uuid);
            instrumentForLookup.setType(type);

			List<String> imagesUrls = service.getInstrumentImagesAsUrls(instrumentForLookup);

			return ResponseEntity.ok().body(imagesUrls);
		} catch (Exception e) { // temporary
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("image")
	public ResponseEntity<Resource> getInstrumentImage(@RequestParam String id, @RequestParam String instrumentType,
			@RequestParam String filename) {

        Instrument instrument = Instrument.fromInstrumentType(InstrumentType.valueOf(instrumentType));
        instrument.setId(UUID.fromString(id));


		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(
				service.getAsResource(instrument, filename));
	}

}
