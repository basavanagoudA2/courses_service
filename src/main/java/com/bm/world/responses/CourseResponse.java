package com.bm.world.responses;

import java.util.Date;

import lombok.Data;

@Data
public class CourseResponse {
	private String courseName;
	private String duration;
	private String faculityName;
	private long fees;
	private String contactNumber;
	private Date startDate;
	private Date endDate;
}
