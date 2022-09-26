package com.bm.world.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoursesServiceControllerAdvice {

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleFieldsValidationExceptions(ConstraintViolationException exception) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(exception.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
