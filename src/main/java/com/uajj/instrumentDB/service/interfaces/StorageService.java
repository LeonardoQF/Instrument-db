package com.uajj.instrumentDB.service.interfaces;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.instrumentDB.model.entities.Instrument;
import com.uajj.instrumentDB.model.entities.InstrumentRegistry;

public interface StorageService {
	
	void init();
	
	Path createFolder(Instrument instrument);
	
	void storeFile(MultipartFile file, Instrument instrument);
	
	Resource getAsResource(InstrumentRegistry registry, String filename);

	

}
