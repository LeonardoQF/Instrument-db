package com.uajj.instrumentDB.service.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import com.uajj.instrumentDB.service.exceptions.InstrumentTypeMismatchException;
import com.uajj.instrumentDB.service.exceptions.NoSuchInstrumentException;
import com.uajj.instrumentDB.service.exceptions.NoSuchInstrumentTypeException;
import com.uajj.instrumentDB.service.exceptions.StandardHttpError;
import com.uajj.instrumentDB.util.ExceptionHandling;

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
	
	@ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
	public ResponseEntity<StandardHttpError> resolveMethodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException e, HttpServletRequest request) {
		return ExceptionHandling.populateStandardHttpError(e, request, "Invalid parameter type or value: " + e.getPropertyName(), HttpStatus.BAD_REQUEST);
	}
	
	
	

}
