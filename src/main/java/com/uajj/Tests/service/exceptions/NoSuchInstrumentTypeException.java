package com.uajj.Tests.service.exceptions;

public class NoSuchInstrumentTypeException extends RuntimeException {

	private static final long serialVersionUID = -677427866047361573L;
	
	public NoSuchInstrumentTypeException(String message) {
		super(message);
	}

}
