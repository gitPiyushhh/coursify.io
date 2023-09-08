package com.CourseApp.Course_app.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.CourseApp.Course_app.Model.Course;
import com.CourseApp.Course_app.Model.Student;

@SuppressWarnings("all")
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom methods
    Student findByFirstNameAndLastName(String firstName, String lastName);    
}