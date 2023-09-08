package com.CourseApp.Course_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("all")
@SpringBootApplication
public class CourseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseAppApplication.class, args);
		System.out.println("////////////////////////////// Running /////////////////////////");
	}

}

// Student - Course Registration

 

// Expose following APIs in Controller

 

// 1) Create Course -- createCourse()

// 2) List of courses available -- getAllCourses()

// 3) Create Student -- createUser()

// 4) Link student to available course -- 

// 5) List all students registered for particular course based on course name -- getUserFromCourse()

// 6) List all courses linked to Student based on student firstname and lastname combination. -- getAllCoursesFromName()

// 7) Delink student from a particular course -- 

// 8) Delete Student -- deleteStudent()

// 9) Delete Course (Course cannot be deleted if any student is linked) -- deleteCourse()

 

// Student Properties - Id, FirstName, LastName, Email 

// Course Properties - CourseId, CourseName

 

// Points to be taken care - 

// 1) FirstName cannot be empty while adding student.

// 2) CourseName cannot be empty.

// 3) CourseName cannot be duplicate.

// 4) One Student can be registered to multiple Courses and also One Course can have multiple Students.

// 5) Use JPARepository for DB Transactions.

 