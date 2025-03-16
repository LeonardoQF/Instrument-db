package com.uajj.Tests.util;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uajj.Tests.service.exceptions.StandardHttpError;

import jakarta.servlet.http.HttpServletRequest;

public class ExceptionHandling {

	/**
	 * 
	 * @param e - The exception to be treated
	 * @param request - The request in which the exception happened.
	 * @return
	 */
	public static ResponseEntity<StandardHttpError> populateStandardHttpError(Exception e, HttpServletRequest request, HttpStatus httpStatus) {
		int status = httpStatus.value();
		String message = e.getMessage();
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.status(status).body(error);
	}
	
	public static ResponseEntity<StandardHttpError> populateStandardHttpError(Exception e, HttpServletRequest request, String customMessage, HttpStatus httpstatus) {
		int status = httpstatus.value();
		String message = customMessage;
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.status(status).body(error);
	}
	
}
