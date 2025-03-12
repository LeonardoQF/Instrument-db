package com.uajj.Tests.service.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uajj.Tests.service.exceptions.StandardHttpError;
import com.uajj.Tests.service.exceptions.StorageException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ControllerAdvice
public class StorageExceptionHandler {

	@ExceptionHandler(exception = StorageException.class)
	public ResponseEntity<StandardHttpError> resolveStorageException(StorageException e, HttpServletRequest request) {

		int status = HttpStatus.BAD_REQUEST.value();
		String message = e.getMessage();
		String path = request.getRequestURI();
		Instant currentTime = Instant.now();

		StandardHttpError error = new StandardHttpError(currentTime, status, message, path);

		return ResponseEntity.badRequest().body(error);
	}
}
