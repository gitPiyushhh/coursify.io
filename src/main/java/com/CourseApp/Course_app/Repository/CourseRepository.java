package com.CourseApp.Course_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CourseApp.Course_app.Model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseName(String courseName);
    List<Course> findAll();

    @Query("SELECT c FROM Course c INNER JOIN c.students s WHERE s.firstName = :firstName AND s.lastName = :lastName")
    List<Course> findCoursesByStudentName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}

