package com.bm.world.controllers;

import javax.validation.Valid;

import com.bm.world.responses.CourseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bm.world.ApplicationConstants;
import com.bm.world.request.CourseRequest;
import com.bm.world.service.CoursesService;

import java.util.List;

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
	@GetMapping(value = ApplicationConstants.GET_ALL_COURSE_DETAILS)
	public ResponseEntity<List<CourseResponse>> getAllCourses(){
		LOG.info("Reqest came to Controller class");
		List<CourseResponse> courseResponseList=coursesService.getAllCourses();
		//coursesService.deleteCache();
		LOG.info("Response getting from service class and dispatch the response");
		return new ResponseEntity<>(courseResponseList,HttpStatus.ACCEPTED);
	}

}
