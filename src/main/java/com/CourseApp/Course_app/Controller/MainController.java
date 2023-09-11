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
@RequestMapping("/api")
public class MainController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 1. Create student
    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/students")
    public String createStudent(@Valid @RequestBody Student student) {
        System.out.println("Inn the Create student controller");
        studentRepository.save(student);
        System.out.println("Doneee hereee......");
        return "Done";
    }

    // 2. Create course
    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/courses")
    public String createCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return "Done";
    }

    // @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/students")
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return ResponseEntity.ok(students);
    }

    // 3. Get all courses
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseRepository.findAll(Sort.by(Sort.Order.asc("courseId")));
        return ResponseEntity.ok(courses);
    }

    // 4. Linking student to course
    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/courses/{courseId}/students/{studentId}")
    public void linkStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(null);
        Student student = studentRepository.findById(studentId).orElseThrow(null);

        System.out.println("Linked the student....");
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    // 5. Get all students for a course
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/courses/{courseId}/students")
    public List<Student> getStudentsByCourse(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(null);
        return course.getStudents();
    }

    // 6. List of courses from first and last name
    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/courses/{firstName}/{lastName}")
    public ResponseEntity<List<Course>> getCoursesByStudentName(
            @PathVariable String firstName,
            @PathVariable String lastName) {
        List<Course> courses = courseRepository.findCoursesByStudentName(firstName, lastName);

        if (!courses.isEmpty()) {
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 7. Delink student
    @PreAuthorize("hasRole('NORMAL')")
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<String> delinkStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            if (course.getStudents().contains(student)) {
                course.getStudents().remove(student);
                courseRepository.save(course);
                return ResponseEntity.ok("Student delinked from course successfully.");
            } else {
                return ResponseEntity.badRequest().body("Student is not linked to the course.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('NORMAL')")
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        // Check if the student exists
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return ResponseEntity.ok("Student deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }   

    // 8. Delete course
    @PreAuthorize("hasRole('NORMAL')")
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            if (course.getStudents().isEmpty()) {
                courseRepository.delete(course);
                System.out.println("Deleted course successfully");
                return ResponseEntity.ok("Course deleted successfully.");
            } else {
                return ResponseEntity.badRequest().body("Cannot delete course with linked students.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
