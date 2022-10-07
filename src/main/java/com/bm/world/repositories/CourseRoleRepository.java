package com.bm.world.repositories;

import com.bm.world.domain.CourseRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRoleRepository extends JpaRepository<CourseRole,Long> {
   CourseRole findByName(String roleName);
}
