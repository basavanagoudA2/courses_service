package com.bm.world.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class CoursesController {
	// private final static Class<? extends Class> log=Logger.class.getClass()t

	private static final Logger LOG = LogManager.getLogger(CoursesController.class);
	@Autowired
	CoursesService coursesService;

	@PostMapping(value = ApplicationConstants.SAVE_COURSE_DETAILS, consumes = "Application/json", produces = "Application/json")
	public ResponseEntity<String> addCoursesDetails(@RequestBody @Valid CourseRequest courseRequest) {
		LOG.info("Request come to Controller class:");
		String response = coursesService.addCourse(courseRequest);
		LOG.info("Response getting from service class and dispatch the response");
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = ApplicationConstants.UPDATE_COURSE_DETAILS, consumes = "Application/json", produces = "Application/json")
	public ResponseEntity<String> updateCoursesDetails(@Valid @RequestBody CourseRequest courseRequest) {
		LOG.info("Request come to Controller class:");
		String response = coursesService.updateCourse(courseRequest);
		LOG.info("Response getting from service class and dispatch the response");
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}
