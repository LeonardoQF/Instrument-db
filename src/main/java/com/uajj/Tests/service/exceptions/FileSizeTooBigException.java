package com.uajj.Tests.service.exceptions;

public class FileSizeTooBigException extends RuntimeException {

	private static final long serialVersionUID = -1398635435601261991L;
	public FileSizeTooBigException(String msg) {
		super(msg);
	}

}
