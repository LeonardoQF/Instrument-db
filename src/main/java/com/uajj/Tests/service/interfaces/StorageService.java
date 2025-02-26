package com.uajj.Tests.service.interfaces;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.Instrument;

public interface StorageService {
	
	void init();
	
	Path createFolder(Instrument instrument);
	
	void storeFile(MultipartFile file);
	
	Resource getAsResource(String filename);

}
