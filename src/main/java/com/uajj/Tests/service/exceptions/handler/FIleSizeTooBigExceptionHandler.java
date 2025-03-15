package com.uajj.Tests.service.exceptions.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.uajj.Tests.config.StorageProperties;
import com.uajj.Tests.service.exceptions.StandardHttpError;
import com.uajj.Tests.util.ExceptionHandling;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class FIleSizeTooBigExceptionHandler {
	
	@Autowired
	StorageProperties props;
	
	@ExceptionHandler(exception = MaxUploadSizeExceededException.class)
	public ResponseEntity<StandardHttpError> resolveMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request){
		return ExceptionHandling.populateStandardHttpError(e, request, "File size too big, maximum allowed is " + props.getMaxImageSizeMegabytesString());
	}

}
