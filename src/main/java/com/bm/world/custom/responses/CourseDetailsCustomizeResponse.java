package com.bm.world.custom.responses;

import lombok.Data;

import java.util.Date;
@Data
public class CourseDetailsCustomizeResponse {
    private String courseName;
    private String duration;
    private String facultyName;
    private long fees;
    private String contactNumber;
    private Date startDate;
}
