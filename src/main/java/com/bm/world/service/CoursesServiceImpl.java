package com.bm.world.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bm.world.ApplicationConstants;
import com.bm.world.model.Courses;
import com.bm.world.repositories.CoursesRepository;
import com.bm.world.request.CourseRequest;
import com.bm.world.responses.CourseResponse;

/**
 * This service class contains all the crude operations implementations for
 * CoursesService class
 * 
 * @author goud_a
 *
 */
@Service
public class CoursesServiceImpl implements CoursesService {
	@Autowired
	CoursesRepository coursesRepository;
      private static final Logger LOG=LogManager.getLogger(CoursesServiceImpl.class);
	/**
	 * This method used for adding the course details/save the course details into
	 * db
	 */
	@Override
	public String addCourse(CourseRequest courseRequest) {
		String response = "";
		LOG.info("Request recieved for save course details: [{}]",courseRequest);
		if (!ObjectUtils.isEmpty(courseRequest)) {
			Courses courses = new Courses();
			BeanUtils.copyProperties(courseRequest, courses);
			coursesRepository.save(courses);
			LOG.debug("saved the course details:[{}]",courses);
			response = "Course details saved with this course_id: " + courses.getCourseId();
		}
		LOG.info("Request processed done and send back to response to controller");
		return response;
	}

	@Override
	public String updateCourse(CourseRequest courseRequest) {
		LOG.info("Recieve the update request:[{}]",courseRequest);
		Courses course = coursesRepository.getReferenceById(courseRequest.getCourseId());
		BeanUtils.copyProperties(courseRequest, course);
		coursesRepository.save(course);
		LOG.debug("update the course details: [{}]",course);
		LOG.info("update the course and send response");
		return ApplicationConstants.COURSE_UPDATE_SUCESS_MESSAGE + courseRequest.getCourseId();
	}

	@Override
	public String deleteCourse(long courseId) {
		return null;
	}

	@Override
	public CourseResponse getCourseDetailsByCouresName(String coureName) {
		return null;
	}

	@Override
	public List<CourseResponse> getAllCourses() {
		return null;
	}

	@Override
	public CourseResponse getCourseDetailsByFacultyName(String facultyName) {
		return null;
	}

}
