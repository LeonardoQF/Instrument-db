package com.uajj.Tests.service.exceptions;

public class InstrumentTypeMismatchException extends RuntimeException {

	private static final long serialVersionUID = 2969417182835385582L;
	
	public InstrumentTypeMismatchException(String msg) {
		super(msg);
	}

}
