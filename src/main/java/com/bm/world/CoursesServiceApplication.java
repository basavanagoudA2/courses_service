package com.bm.world;

import com.bm.world.domain.CourseRole;
import com.bm.world.domain.CourseUser;
import com.bm.world.service.CourseUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CoursesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CourseUserService courseUserService){
		return args->{
		    courseUserService.saveRole(new CourseRole(null,"ROLE_USER"));
			courseUserService.saveRole(new CourseRole(null,"ROLE_MANAGER"));
			courseUserService.saveRole(new CourseRole(null,"ROLE_ADMIN"));
			courseUserService.saveRole(new CourseRole(null,"ROLE_SUPER_ADMIN)"));
			// populate the user details
			courseUserService.saveUser(new CourseUser(null,"Kantha Reddy A","Kantha_a","1234",new ArrayList<>()));
			courseUserService.saveUser(new CourseUser(null,"Basamma A","basamma_a","1234",new ArrayList<>()));
			courseUserService.saveUser(new CourseUser(null,"Basavana Goud A","goud_a","1234",new ArrayList<>()));
			courseUserService.saveUser(new CourseUser(null,"Mamatha A","mamatha_a","1234",new ArrayList<>()));
			courseUserService.saveUser(new CourseUser(null,"Malli Karjuna A","malli_a","1234",new ArrayList<>()));
			// add RoleToUser
			courseUserService.addRoleToUser("kantha_a","ROLE_SUPER_ADMIN");
			courseUserService.addRoleToUser("basamma_a","ROLE_ADMIN");
			courseUserService.addRoleToUser("goud_a","ROLE_USER");
			courseUserService.addRoleToUser("mamatha_a","ROLE_USER");
			courseUserService.addRoleToUser("malli_a","ROLE_USER");
		};
	}
	
}
