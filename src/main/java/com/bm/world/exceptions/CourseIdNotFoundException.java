package com.bm.world.exceptions;

import java.time.LocalTime;

public class CourseIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;
    LocalTime time;
	public CourseIdNotFoundException(String errorMessage, LocalTime time) {
		super();
		this.errorMessage = errorMessage;
		this.time = time;
	}
	public CourseIdNotFoundException() {
		super();
		
	}
	public CourseIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public CourseIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public CourseIdNotFoundException(String message) {
		super(message);
	}
	public CourseIdNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
