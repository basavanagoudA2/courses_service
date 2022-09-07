package com.bm.world.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.bm.world.ApplicationConstants;

import lombok.Data;
/**
 * This entity class for crude operations on courses
 * @author goud_a
 *
 */
@Data
@Entity
@Table(name = ApplicationConstants.TABLENAME_COURSES_INFO)
public class Courses  {
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	@Column(name = "course_name")
	@NotBlank(message = "courseName should not empty")
	private String courseName;
	@Column(name = "duration")
	private String duration;
	@Column(name = "faculity_name")
	private String faculityName;
	@Column(name = "fees")
	private long fees;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
}
