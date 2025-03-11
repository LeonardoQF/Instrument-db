package com.uajj.Tests.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config.storage")
public class StorageProperties {

	private String pathName;

	private List<String> supportedImageTypes;

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

}
