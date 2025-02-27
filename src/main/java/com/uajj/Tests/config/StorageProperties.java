package com.uajj.Tests.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {
	
	@Primary
	@Bean
	static StorageProperties getDefaultProperties() {
		return new StorageProperties();
		
	}
	
	private String pathName;
	
	public String getPathName() {
		return pathName;
	}
	
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
}
