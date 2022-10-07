package com.bm.world.service;

import com.bm.world.domain.CourseRole;
import com.bm.world.domain.CourseUser;

import java.util.List;

public interface CourseUserService {
    CourseUser saveUser(CourseUser courseUser);
    CourseRole saveRole(CourseRole courseRole);
    void addRoleToUser(String userName,String roleName);
    CourseUser getUser(String Username);
    List<CourseUser> getUsers();
}
