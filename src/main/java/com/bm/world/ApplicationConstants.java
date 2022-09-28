package com.bm.world;

public class ApplicationConstants {
	private ApplicationConstants() {}
	public static final String TABLENAME_COURSES_INFO="courses_info";
	//Declare the Controller class Constants
	 public static final String BASE_URL="/bm/course";
	 public static final String SAVE_COURSE_DETAILS="/save";
	 public static final String UPDATE_COURSE_DETAILS="/update";
	 public static final String GET_ALL_COURSE_DETAILS="/all";
	 public static final String GET_COURSE_DETAILS_BY_FACULTY_NAME="/{facultyName}";
	 public static final String GET_NEXT_MONTH_COURSE_DETAILS="/nextmonth";
	 
	 //success message constraints
	 public static final String COURSE_UPDATE_SUCESS_MESSAGE="course is updated with this courseId :";
	

}
