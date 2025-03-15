package com.uajj.Tests.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config.storage")
public class StorageProperties {

	private String pathName;

	private List<String> supportedImageTypes;

	private Long maxImageSizeBytes;

	private int maxImagesPerFolder;
	
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxImageSizeMegabytesString;

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public List<String> getSupportedImageTypes() {
		return supportedImageTypes;
	}

	public void setSupportedImageTypes(List<String> supportedImageTypes) {
		this.supportedImageTypes = supportedImageTypes;
	}

	public Long getMaxImageSizeBytes() {
		return maxImageSizeBytes;
	}

	public void setMaxImageSizeBytes(Long maxImageSizeBytes) {
		this.maxImageSizeBytes = maxImageSizeBytes;
	}

	public int getMaxImagesPerFolder() {
		return maxImagesPerFolder;
	}

	public void setMaxImagesPerFolder(int maxImagesPerFolder) {
		this.maxImagesPerFolder = maxImagesPerFolder;
	}

	public String getMaxImageSizeMegabytesString() {
		return maxImageSizeMegabytesString;
	}

	public void setMaxImageSizeMegabytesString(String maxImageSizeMegabytesString) {
		this.maxImageSizeMegabytesString = maxImageSizeMegabytesString;
	}
	
	

}
