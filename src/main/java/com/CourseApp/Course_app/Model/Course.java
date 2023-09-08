package com.CourseApp.Course_app.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@SuppressWarnings("all")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private Long courseId;

    @Column(name = "courseName", nullable = false, unique = true)
    private String courseName;

    @ManyToMany
    @JoinTable(name = "courseStudent", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns = @JoinColumn(name = "studentId"))
    List<Student> students = new ArrayList<>();

    public Course() {

    }

    public Course(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Custom method -- To get the list of students linked to a course
    public List<Student> getStudents() {
        return students;
    }

}
