package com.bm.world.controllers;

import com.bm.world.ApplicationConstants;
import com.bm.world.custom.responses.CourseDetailsCustomizeResponse;
import com.bm.world.request.CourseRequest;
import com.bm.world.responses.CourseResponse;
import com.bm.world.service.CoursesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = ApplicationConstants.BASE_URL)
public class CoursesController {
	private static final  String RESPONSE_LOG_MESSAGE="Response getting from service class and dispatch the response";
	private static final Logger LOG = LogManager.getLogger(CoursesController.class);
	@Autowired
	CoursesService coursesService;

	@PostMapping(value = ApplicationConstants.SAVE_COURSE_DETAILS, consumes = "Application/json", produces = "Application/json")
	public ResponseEntity<String> addCoursesDetails(@RequestBody @Valid CourseRequest courseRequest) {
		LOG.info("Request come to Controller class:");
		String response = coursesService.addCourse(courseRequest);
		LOG.info(RESPONSE_LOG_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = ApplicationConstants.UPDATE_COURSE_DETAILS, consumes = "Application/json", produces = "Application/json")
	public ResponseEntity<String> updateCoursesDetails(@Valid @RequestBody CourseRequest courseRequest) {
		LOG.info("Request come to Controller class:");
		String response = coursesService.updateCourse(courseRequest);
		LOG.info("Response getting from service class and dispatch the response:[{}]",response);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping(value = ApplicationConstants.GET_ALL_COURSE_DETAILS)
	public ResponseEntity<List<CourseResponse>> getAllCourses(){
		LOG.info("Request came to Controller class");
		List<CourseResponse> courseResponseList = coursesService.getAllCourses();
		LOG.info(RESPONSE_LOG_MESSAGE);
		return new ResponseEntity<>(courseResponseList,HttpStatus.ACCEPTED);
	}
    @GetMapping(value = ApplicationConstants.GET_COURSE_DETAILS_BY_FACULTY_NAME)
	public ResponseEntity<List<CourseDetailsCustomizeResponse>> getCourseDetailsByFacultyName(@PathVariable String facultyName) throws ParseException {
		LOG.info("Request came to Controller class");
		List<CourseDetailsCustomizeResponse> courseResponseList=coursesService.getDetailsByFacultyName(facultyName);
		LOG.info(RESPONSE_LOG_MESSAGE);
		return new ResponseEntity<>(courseResponseList,HttpStatus.ACCEPTED);
	}
	@GetMapping(value =ApplicationConstants.GET_NEXT_MONTH_COURSE_DETAILS)
	public ResponseEntity<List<CourseDetailsCustomizeResponse>> getNextMonthsCourses(){
		LOG.info("Starting fetch the next month course details");
		List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList=coursesService.getNextMonthCourses();
		LOG.info(RESPONSE_LOG_MESSAGE);
		return new ResponseEntity<>(courseDetailsCustomizeResponseList,HttpStatus.ACCEPTED);
	}
}
