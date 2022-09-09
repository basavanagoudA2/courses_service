package com.bm.world.exceptions;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoursesServiceControllerAdvice {
	
	@ExceptionHandler(value =ConstraintViolationException.class )
	public ResponseEntity<ApiError> handleFieldsValidationExceptions(ConstraintViolationException exception) {
		String errorMessage=new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
		ApiError apiError=new ApiError(errorMessage, LocalTime.now(), 1000l);
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}
	
	

}
