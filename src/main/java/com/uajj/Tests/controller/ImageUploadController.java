package com.uajj.Tests.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping
public class ImageUploadController {
	
	
	@PostMapping
	public ResponseEntity<String> uploadImage(MultipartFile file) {
		//TODO image upload logic, structure is temporary
		try {
			
			
		
		
		return ResponseEntity.ok("Image uploaded!");
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body("Error uploading image");
		}
	}
	
	
}
