package com.uajj.Tests.service.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uajj.Tests.service.exceptions.StandardHttpError;
import com.uajj.Tests.service.exceptions.StorageException;
import com.uajj.Tests.util.ExceptionHandling;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@ControllerAdvice
public class StorageExceptionHandler {

	@ExceptionHandler(exception = StorageException.class)
	public ResponseEntity<StandardHttpError> resolveStorageException(StorageException e, HttpServletRequest request) {
		return ExceptionHandling.populateStandardHttpError(e, request);
	}
}
