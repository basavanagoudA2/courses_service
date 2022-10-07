package com.bm.world.repositories;

import com.bm.world.domain.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseUserRepository extends JpaRepository<CourseUser,Long> {
    CourseUser findByUserName(String userName);
}
