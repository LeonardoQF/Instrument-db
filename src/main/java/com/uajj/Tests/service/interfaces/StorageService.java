package com.uajj.Tests.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();
	
	void storeFile(MultipartFile file);
	
	Resource getAsResource(String filename);

}
