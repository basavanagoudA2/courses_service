package com.bm.world.service;

import java.text.ParseException;
import java.util.List;

import com.bm.world.custom.responses.CourseDetailsCustomizeResponse;
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
	public CourseResponse getCourseDetailsByCourseName(String courseName);
	public List<CourseResponse> getAllCourses();
	public List<CourseDetailsCustomizeResponse> getDetailsByFacultyName(String facultyName) throws ParseException;
	public void deleteCache();
	public List<CourseDetailsCustomizeResponse> getNextMonthCourses();
}
