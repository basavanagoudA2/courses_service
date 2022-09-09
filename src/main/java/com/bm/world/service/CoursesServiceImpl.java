package com.bm.world.service;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bm.world.ApplicationConstants;
import com.bm.world.exceptions.CourseIdNotFoundException;
import com.bm.world.model.Courses;
import com.bm.world.repositories.CoursesRepository;
import com.bm.world.request.CourseRequest;
import com.bm.world.responses.CourseResponse;
/**
 * This service class contains all the crude operations implementations for CoursesService class
 * @author goud_a
 *
 */
@Service
public class CoursesServiceImpl implements CoursesService,ApplicationConstants {
	@Autowired
	CoursesRepository coursesRepository;
	/**
	 * This method used for adding the course details/save the course details into db
	 */
	@Override
	public String addCourse(CourseRequest courseRequest) {
		String response="";
		if (!ObjectUtils.isEmpty(courseRequest)) {
			Courses courses=new Courses();
			BeanUtils.copyProperties(courseRequest, courses);
			coursesRepository.save(courses);
			response="Course details saved with this course_id: "+courses.getCourseId();
		}
		return response;
	}

	@Override
	public String updateCourse(CourseRequest courseRequest) {
			try {
				Courses course = coursesRepository.getReferenceById(courseRequest.getCourseId());
				BeanUtils.copyProperties(courseRequest, course);
				coursesRepository.save(course);	
			} catch (EntityNotFoundException exception) {
				throw new CourseIdNotFoundException("course is not found for this courseId :"+courseRequest.getCourseId(), LocalTime.now());
			}		
		return COURSE_UPDATE_SUCESS_MESSAGE+courseRequest.getCourseId();
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
		// TODO Auto-generated method stub
		return null;
	}

}
