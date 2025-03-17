package com.uajj.Tests.service.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uajj.Tests.service.exceptions.InstrumentTypeMismatchException;
import com.uajj.Tests.service.exceptions.NoSuchInstrumentException;
import com.uajj.Tests.service.exceptions.NoSuchInstrumentTypeException;
import com.uajj.Tests.service.exceptions.StandardHttpError;
import com.uajj.Tests.util.ExceptionHandling;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RequestExceptionHandler {
	
	@ExceptionHandler(InstrumentTypeMismatchException.class)
	public ResponseEntity<StandardHttpError> resolveInstrumentTypeMismatchException(InstrumentTypeMismatchException e, HttpServletRequest request) {
		return ExceptionHandling.populateStandardHttpError(e, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchInstrumentException.class)
	public ResponseEntity<StandardHttpError> resolveNoSuchInstrumentException(NoSuchInstrumentException e, HttpServletRequest request) {
		return ExceptionHandling.populateStandardHttpError(e, request, "Instrument not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchInstrumentTypeException.class)
	public ResponseEntity<StandardHttpError> resolveNoSuchInstrumentTypeException(NoSuchInstrumentTypeException e, HttpServletRequest request) {
		return ExceptionHandling.populateStandardHttpError(e, request, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
