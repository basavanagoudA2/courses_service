package com.bm.world.exceptions;

public class CourseDetailsNotFoundException extends RuntimeException{
    public CourseDetailsNotFoundException() {
    }

    public CourseDetailsNotFoundException(String message) {
        super(message);
    }
}
