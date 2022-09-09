package com.bm.world.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bm.world.ApplicationConstants;
import com.bm.world.request.CourseRequest;
import com.bm.world.service.CoursesService;

@RestController
@RequestMapping(value = ApplicationConstants.BASE_URL)
public class CoursesController implements ApplicationConstants {
	@Autowired
	CoursesService coursesService;
	
	@PostMapping(value =SAVE_COURSE_DETAILS,consumes = "Application/json",produces = "Application/json")
	public ResponseEntity<String> addCoursesDetails(@RequestBody @Valid CourseRequest courseRequest) {
		String response = coursesService.addCourse(courseRequest);
		return new ResponseEntity<String>(response,HttpStatus.ACCEPTED);		
	}
	
	@PutMapping(value = UPDATE_COURSE_DETAILS,consumes = "Application/json",produces = "Application/json")
	public ResponseEntity<String> updateCoursesDetails(@Valid @RequestBody CourseRequest courseRequest) {
		return new ResponseEntity<String>(coursesService.updateCourse(courseRequest),HttpStatus.ACCEPTED);
		
	}

}
