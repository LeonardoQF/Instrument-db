package com.uajj.instrumentDB.service.interfaces;

import java.nio.file.Path;

import com.uajj.instrumentDB.service.InstrumentService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.uajj.instrumentDB.model.entities.Instrument;

public interface StorageService {
	
	void init();
	
	Path createFolder(Instrument instrument);
	
	void storeFile(MultipartFile file, Instrument instrument);
	
	Resource getAsResource(Instrument instrument, String filename);
}
