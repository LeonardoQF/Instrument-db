package com.uajj.instrumentDB.service.exceptions;

public class NoSuchInstrumentException extends RuntimeException {

	private static final long serialVersionUID = 8609775019016789608L;
	
	public NoSuchInstrumentException(String msg) {
		super(msg);
	}

}
