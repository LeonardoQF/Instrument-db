package com.uajj.Tests.service.interfaces;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.InstrumentRegistry;

public interface StorageService {
	
	void init();
	
	Path createFolder(Instrument instrument);
	
	void storeFile(MultipartFile file, Instrument instrument);
	
	Resource getAsResource(InstrumentRegistry registry, String filename);

	

}
