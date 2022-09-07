package com.bm.world.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class CoursesServiceImpl implements CoursesService {
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
		}else {
			response="course details missing, please try again";
		}
		return response;
	}

	@Override
	public String updateCourse(CourseRequest courseRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCourse(long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponse getCourseDetailsByCouresName(String coureName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseResponse> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseResponse getCourseDetailsByFacultyName(String facultyName) {
		// TODO Auto-generated method stub
		return null;
	}

}
