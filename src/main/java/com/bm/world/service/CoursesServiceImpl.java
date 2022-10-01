package com.bm.world.service;

import com.bm.world.ApplicationConstants;
import com.bm.world.custom.responses.CourseDetailsCustomizeResponse;
import com.bm.world.exceptions.CourseDetailsNotFoundException;
import com.bm.world.model.Courses;
import com.bm.world.repositories.CoursesRepository;
import com.bm.world.request.CourseRequest;
import com.bm.world.responses.CourseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This service class contains all the crude operations implementations for
 * CoursesService class
 *
 * @author goud_a
 */
@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CoursesRepository coursesRepository;
    private static final Logger LOG = LogManager.getLogger(CoursesServiceImpl.class);

    /**
     * This method used for adding the course details/save the course details into
     * db
     */
    @Override
    public String addCourse(CourseRequest courseRequest) {
        String response = "";
        LOG.info("Request received for save course details: [{}]", courseRequest);
        if (!ObjectUtils.isEmpty(courseRequest)) {
            Courses courses = new Courses();
            BeanUtils.copyProperties(courseRequest, courses);
            coursesRepository.save(courses);
            LOG.debug("saved the course details:[{}]", courses);
            response = "Course details saved with this course_id: " + courses.getCourseId();
        }
        LOG.info("Request processed done and send back to response to controller");
        return response;
    }

    @Override
    public String updateCourse(CourseRequest courseRequest) {
        LOG.info("Receive the update request:[{}]", courseRequest);
            Optional<Courses> courses = coursesRepository.findById(courseRequest.getCourseId());
            if (courses.isPresent()) {
                Courses course = courses.get();
                BeanUtils.copyProperties(courseRequest, course);
                coursesRepository.save(course);
                LOG.debug("update the course details: [{}]", course);
                LOG.info("update the course and send response");
            }else {
                throw new CourseDetailsNotFoundException("Course details not available for this courseId update:"+courseRequest.getCourseId());
            }
        return ApplicationConstants.COURSE_UPDATE_SUCESS_MESSAGE + courseRequest.getCourseId();
    }

    @Override
    public String deleteCourse(long courseId) {
        return null;
    }

    @Override
    public CourseResponse getCourseDetailsByCourseName(String courseName) {
        return null;
    }

    /**
     * This method is used for fetching the all the course details from database
     *
     * @return
     */
    @Override
    public List<CourseResponse> getAllCourses() {
        LOG.info("Starting the fetching all course details.");
        List<CourseResponse> courseResponseList = new ArrayList<>();
        CourseResponse courseResponse = null;
        if (ObjectUtils.isEmpty(courseResponseList)) {
            List<Courses> coursesList = coursesRepository.findAll();
            if (!ObjectUtils.isEmpty(coursesList)) {
                for (Courses courses : coursesList) {
                    courseResponse = new CourseResponse();
                    BeanUtils.copyProperties(courses, courseResponse);
                    courseResponseList.add(courseResponse);
                }
                coursesList.clear();
            } else {
                throw new CourseDetailsNotFoundException("Course details not found");
            }
        }
        LOG.debug("fetching All course details [{}].", courseResponseList);
        LOG.info("complete the fetching all course details.");
        return courseResponseList;
    }

    /**
     * This method used for fetching course data based on faculty Name
     *
     * @param facultyName
     * @return
     * @throws ParseException
     */
    @Override
    public List<CourseDetailsCustomizeResponse> getDetailsByFacultyName(String facultyName) throws ParseException {
        LOG.info("Starting the fetching course details by facultyName:[{}]", facultyName);
        CourseDetailsCustomizeResponse courseDetailsCustomizeResponse;
        List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList1 = null;
        courseDetailsCustomizeResponse = new CourseDetailsCustomizeResponse();
        List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList = new ArrayList<>();
        if (courseDetailsCustomizeResponseList.isEmpty()) {
            List<Object[]> objectList = coursesRepository.getCourseDetailsByName(facultyName);
            courseDetailsCustomizeResponseList1 = objectListChecker(objectList, courseDetailsCustomizeResponse, courseDetailsCustomizeResponseList);
        }
        LOG.debug("fetching All course details by facultyName [{}].", courseDetailsCustomizeResponseList1);
        LOG.info("complete fetching All course details by facultyName [{}].", courseDetailsCustomizeResponseList1);
        return courseDetailsCustomizeResponseList1;
    }

    @Override
    public void deleteCache() {
        //not implemented
    }

    /**
     * This method used for fetching the course details in next month
     *
     * @return
     */
    @Override
    public List<CourseDetailsCustomizeResponse> getNextMonthCourses() {
        LOG.info("start the fetching next month course details");
        CourseDetailsCustomizeResponse courseDetailsCustomizeResponse;
        courseDetailsCustomizeResponse = new CourseDetailsCustomizeResponse();
        List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList = new ArrayList<>();
        List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList1 = null;
        if (ObjectUtils.isEmpty(courseDetailsCustomizeResponseList)) {
            List<Object[]> nextMonthCoursesObjectList = coursesRepository.fetchNextMonthCourses();
            courseDetailsCustomizeResponseList1 = objectListChecker(nextMonthCoursesObjectList, courseDetailsCustomizeResponse, courseDetailsCustomizeResponseList);
        }
        return courseDetailsCustomizeResponseList1;
    }

    private List<CourseDetailsCustomizeResponse> objectListChecker(List<Object[]> objectList, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse, List<CourseDetailsCustomizeResponse> courseDetailsCustomizeResponseList) {
        if (!ObjectUtils.isEmpty(objectList)) {
            for (Object[] courseObjectArr : objectList) {
                courseNameChecker(courseObjectArr, courseDetailsCustomizeResponse);
                courseDetailsCustomizeResponseList.add(courseDetailsCustomizeResponse);
            }
        } else {
            throw new CourseDetailsNotFoundException("Courses are not found for next Month");
        }
        return courseDetailsCustomizeResponseList;
    }

    private void courseNameChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[0])) {
            courseDetailsCustomizeResponse.setCourseName(String.valueOf(courseObjectArr[0]));
        }
        durationChecker(courseObjectArr, courseDetailsCustomizeResponse);
    }

    private void durationChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[1])) {
            courseDetailsCustomizeResponse.setDuration(String.valueOf(courseObjectArr[1]));
        }
        feesChecker(courseObjectArr, courseDetailsCustomizeResponse);
    }

    private void feesChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[2])) {
            courseDetailsCustomizeResponse.setFees(Long.valueOf(String.valueOf(courseObjectArr[2])));
        }
        startDateChecker(courseObjectArr, courseDetailsCustomizeResponse);
    }

    private void startDateChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[3])) {
            courseDetailsCustomizeResponse.setStartDate((Date) courseObjectArr[3]);
        }
        facultyNameChecker(courseObjectArr, courseDetailsCustomizeResponse);
    }

    private void facultyNameChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[4])) {
            courseDetailsCustomizeResponse.setFacultyName(String.valueOf(courseObjectArr[4]));
        }
        contactNumberChecker(courseObjectArr, courseDetailsCustomizeResponse);
    }

    private void contactNumberChecker(Object[] courseObjectArr, CourseDetailsCustomizeResponse courseDetailsCustomizeResponse) {
        if (!ObjectUtils.isEmpty(courseObjectArr[5])) {
            courseDetailsCustomizeResponse.setContactNumber(String.valueOf(courseObjectArr[5]));
        }
    }

}
