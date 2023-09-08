package com.CourseApp.Course_app.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CourseApp.Course_app.Model.Course;
import com.CourseApp.Course_app.Model.Student;
import com.CourseApp.Course_app.Repository.CourseRepository;
import com.CourseApp.Course_app.Repository.StudentRepository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@SuppressWarnings("all")
@RestController
public class ClientController {
    // 0. Root route
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/")
    @ResponseBody
    public String root() {
        try {
            Resource resource = new ClassPathResource("static/index.html");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cant't find the html file";
    }

    // 1. Get the courses
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/courses")
    @ResponseBody
    public String getCourses() {
        try {
            Resource resource = new ClassPathResource("static/courses.html");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cant't find the html file";
    }

    // 2. Get the students
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/students")
    @ResponseBody
    public String getStudents() {
        try {
            Resource resource = new ClassPathResource("static/students.html");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cant't find the html file";
    }
}

