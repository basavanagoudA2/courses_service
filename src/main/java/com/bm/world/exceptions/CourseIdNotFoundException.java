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
		// TODO Auto-generated constructor stub
	}
	public CourseIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CourseIdNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CourseIdNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
