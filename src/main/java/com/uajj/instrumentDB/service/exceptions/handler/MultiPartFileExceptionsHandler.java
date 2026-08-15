package com.uajj.instrumentDB.service.exceptions.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.uajj.instrumentDB.config.StorageProperties;
import com.uajj.instrumentDB.service.exceptions.StandardHttpError;
import com.uajj.instrumentDB.util.ExceptionHandling;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MultiPartFileExceptionsHandler {
	
	@Autowired
	StorageProperties props;
	
	@ExceptionHandler(exception = MaxUploadSizeExceededException.class)
	public ResponseEntity<StandardHttpError> resolveMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request){
		return ExceptionHandling.populateStandardHttpError(e, request, "File size is too big: maximum allowed is " + props.getMaxImageSizeMegabytesString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(exception = MissingServletRequestPartException.class)
	public ResponseEntity<StandardHttpError> resolveMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request){
		return ExceptionHandling.populateStandardHttpError(e, request, "File cannot be Empty", HttpStatus.BAD_REQUEST);
	}
	

}
