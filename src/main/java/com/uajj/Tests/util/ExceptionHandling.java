package com.uajj.Tests.util;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uajj.Tests.service.exceptions.StandardHttpError;

import jakarta.servlet.http.HttpServletRequest;

public class ExceptionHandling {

	/**
	 * Populates a StandardHttpError. This method overload has no custom message parameter to be passed: The error message will then be the provided exception's error message.
	 * @param e - The exception to be treated
	 * @param request - The request in which the exception happened.
	 * @return - A response entity with the passed status code and populated error.
	 */
	public static ResponseEntity<StandardHttpError> populateStandardHttpError(Exception e, HttpServletRequest request, HttpStatus httpStatus) {
		int status = httpStatus.value();
		String message = e.getMessage();
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.status(status).body(error);
	}
	
	/**
	 * Populates a StandardHttpError. This method overload allows a custom message to be used, instead of the standard exception's message.
	 * @param e - The exception to be treated
	 * @param request - The request in which the exception happened.
	 * @param customMessage - The custom message which will be passed to the request's "error" field.
	 * @return - A response entity with the passed status code and populated error.
	 */
	public static ResponseEntity<StandardHttpError> populateStandardHttpError(Exception e, HttpServletRequest request, String customMessage, HttpStatus httpstatus) {
		int status = httpstatus.value();
		String message = customMessage;
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.status(status).body(error);
	}
	
}
