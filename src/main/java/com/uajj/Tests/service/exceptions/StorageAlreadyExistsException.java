package com.uajj.Tests.service.exceptions;

public class StorageAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 10534486153204744L;
	
	public StorageAlreadyExistsException(String message) {
		super(message);
	}

}
