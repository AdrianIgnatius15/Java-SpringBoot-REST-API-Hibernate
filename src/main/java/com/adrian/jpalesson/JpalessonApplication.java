package com.adrian.jpalesson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.entities.FullTimeEmployee;
import com.adrian.jpalesson.entities.PartTimeEmployee;
import com.adrian.jpalesson.entities.Passport;
import com.adrian.jpalesson.entities.Review;
import com.adrian.jpalesson.entities.Student;
import com.adrian.jpalesson.repositories.CourseRepository;
import com.adrian.jpalesson.repositories.EmployeeRepository;
import com.adrian.jpalesson.repositories.StudentRepository;

@SpringBootApplication
public class JpalessonApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private Logger logger = LoggerFactory.getLogger(JpalessonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpalessonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// logger.info("Getting course of id 10001 --> {}", courseRepository.getCourseById(10001));
		// List<Review> reviews = new ArrayList<>();
		// reviews.add(new Review("5", "Bravo"));
		// reviews.add(new Review("4", "Fantastic course!"));

		// logger.info("Playing with entity manager is now executed...");
		// courseRepository.playingWithEntityManager();
		// studentRepository.saveStudentWithPassport();
		// courseRepository.addReviewsToCourse(10001, reviews);
		// logger.info("Student from repository --> {}", studentRepository.getStudentWithCourses(20001));
		// Student student = new Student("Petra Charlotte Verkaik");
		// Passport passport = new Passport("US79185");
		// Course course = new Course("Marriage Course");

		// studentRepository.createStudentWithCourseAndPassport(student, course, passport);
		FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("Jack", new BigDecimal("50000"));
		PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Jill", new BigDecimal("50"));
		employeeRepository.insertEmployee(fullTimeEmployee);
		employeeRepository.insertEmployee(partTimeEmployee);

		// logger.info("Getting all employees --> {}", employeeRepository.getAllEmployees());
		logger.info("Getting all full time employees --> {}", employeeRepository.getAllFullTimeEmployees());
		logger.info("Getting all part time employees --> {}", employeeRepository.getAllPartTimeEmployees());
	}

}
