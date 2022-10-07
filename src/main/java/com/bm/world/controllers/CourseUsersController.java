package com.bm.world.controllers;

import com.bm.world.ApplicationConstants;
import com.bm.world.domain.CourseUser;
import com.bm.world.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApplicationConstants.API)
public class CourseUsersController {

    private final CourseUserService courseUserService;

    @GetMapping(value = ApplicationConstants.USER_GET)
    public ResponseEntity<List<CourseUser>> getUsers() {
        return ResponseEntity.ok().body( courseUserService.getUsers());
    }
}
