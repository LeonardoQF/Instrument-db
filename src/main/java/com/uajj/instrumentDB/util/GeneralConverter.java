package com.uajj.instrumentDB.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class GeneralConverter {
	
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
	
	/**
	 * Converts bytes to megabytes by dividing the bytes by 2^20
	 * @param bytes
	 * @return
	 */
	public static String convertToMegabytesString(long bytes) {
		DataSize size = DataSize.ofBytes(bytes);
		
		return "" + size.toMegabytes() + "MB";
	}

}
