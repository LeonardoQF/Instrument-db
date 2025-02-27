package com.uajj.Tests.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileConverter {
	
	public static File fromMultiPartFile(MultipartFile file) throws IOException {
		
		System.out.println(file.getOriginalFilename());
		
		File convertedFile = new File(file.getOriginalFilename());
		byte[] originalFileBytes = file.getBytes();
		
		try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
			convertedFile.createNewFile();
			
			fos.write(originalFileBytes);
		} catch(IOException e) {
			convertedFile = null;
		}
		
		return convertedFile;
		
	}

}
