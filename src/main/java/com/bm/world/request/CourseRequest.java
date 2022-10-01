package com.bm.world.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CourseRequest {
	private long courseId;
	private String courseName;
	private String duration;
	private String facultyName;
	private long fees;
	private String contactNumber;	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date endDate;
}
