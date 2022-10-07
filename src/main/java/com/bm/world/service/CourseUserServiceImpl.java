package com.bm.world.service;

import com.bm.world.domain.CourseRole;
import com.bm.world.domain.CourseUser;
import com.bm.world.repositories.CourseRoleRepository;
import com.bm.world.repositories.CourseUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class CourseUserServiceImpl implements CourseUserService {
    private final CourseUserRepository courseUserRepository;
    private final CourseRoleRepository courseRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public CourseUser saveUser(CourseUser courseUser) {
        courseUser.setPassword(passwordEncoder.encode(courseUser.getPassword()));
        log.info("Starting save the Course user :{}", courseUser);
        return courseUserRepository.save(courseUser);
    }

    @Override
    public CourseRole saveRole(CourseRole courseRole) {
        log.info("saving the Role :{}", courseRole);
        return courseRoleRepository.save(courseRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("add the user {}  RoleName  to :{}",userName, roleName);
        CourseUser courseUser = courseUserRepository.findByUserName(userName);
        CourseRole role = courseRoleRepository.findByName(roleName);
        courseUser.getRoles().add(role);
    }

    @Override
    public CourseUser getUser(String username) {
        log.info("fetching user information by username:{}",username );
        return courseUserRepository.findByUserName(username);
    }

    @Override
    public List<CourseUser> getUsers() {
        log.info("fetching All users ");
        return courseUserRepository.findAll();
    }
}
