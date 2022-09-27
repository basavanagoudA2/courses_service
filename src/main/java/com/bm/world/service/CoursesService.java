package com.bm.world.service;

import java.util.List;

import com.bm.world.request.CourseRequest;
import com.bm.world.responses.CourseResponse;

/**
 * This interface contains all crude operation methods for the Courses
 * @author goud_a
 *
 */
public interface CoursesService {

	public String addCourse(CourseRequest courseRequest);
	public String updateCourse(CourseRequest courseRequest);
	public String deleteCourse(long courseId);
	public CourseResponse getCourseDetailsByCouresName(String coureName);
	public List<CourseResponse> getAllCourses();
	public CourseResponse getCourseDetailsByFacultyName(String facultyName);
	public void deleteCache();
}
