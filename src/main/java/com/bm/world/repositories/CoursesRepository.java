package com.bm.world.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.world.model.Courses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Long>{
    @Query(value = "SELECT course_name,duration,fees,start_date,faculty_name,contact_number FROM courses_info where faculty_name=:facultyName",nativeQuery = true)
    public List<Object[]> getCourseDetailsByName(@Param("facultyName")String facultyName);
    @Query(value = "SELECT course_name,duration,fees,start_date FROM course_db.courses_info where month(start_date)=month(curdate())+1",nativeQuery = true)
    public List<Object[]> fetchNextMonthCourses();
}
