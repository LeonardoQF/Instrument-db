package com.uajj.Tests.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.service.ImageStorageService;

@Controller
@RequestMapping(path = "/upload_image")
public class ImageUploadController {

	private ImageStorageService service;

	public ImageUploadController(ImageStorageService service) {
		this.service = service;
	}

	
	//TODO Create a solution to receive both the image and the instrument's data
	@PostMapping
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile image,
			@ModelAttribute Instrument instrument) {

		service.storeFile(image, instrument);

		return ResponseEntity.ok("Image uploaded!");

	}

}
