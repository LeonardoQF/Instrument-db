package com.uajj.Tests.util;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uajj.Tests.service.exceptions.StandardHttpError;

import jakarta.servlet.http.HttpServletRequest;

public class ExceptionHandling {

	public static ResponseEntity<StandardHttpError> populateStandardHttpError(RuntimeException e, HttpServletRequest request) {
		int status = HttpStatus.BAD_REQUEST.value();
		String message = e.getMessage();
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.badRequest().body(error);
	}
	
	public static ResponseEntity<StandardHttpError> populateStandardHttpError(RuntimeException e, HttpServletRequest request, String customMessage) {
		int status = HttpStatus.BAD_REQUEST.value();
		String message = customMessage;
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.badRequest().body(error);
	}
	
}
