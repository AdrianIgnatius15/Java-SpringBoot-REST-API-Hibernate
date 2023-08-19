package com.adrian.jpalesson.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adrian.jpalesson.JpalessonApplication;
import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.repositories.CourseRepository;

@SpringBootTest(classes = JpalessonApplication.class)
class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void contextLoads() {
		logger.info("Testing is running	");
		Course course = repository.getCourseById(10001);
		assertEquals(".NET Core REST API with Kubernetes", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteCourseByIdUnitTesting() {
		repository.deleteCourseById(10001);
		assertNull(repository.getCourseById(10001));
	}

	@Test
	@DirtiesContext
	void saveCourse() {
		Course course = repository.getCourseById(10001);
		assertEquals(".NET Core REST API with Kubernetes", course.getName());

		course.setName(".NET Core REST API with Kubernetes --> Updated");
		repository.saveCourse(course);

		Course updatedCourseDetails = repository.getCourseById(10001);
		assertEquals(".NET Core REST API with Kubernetes --> Updated", updatedCourseDetails.getName(), "Successfully matched");
		// repository.saveCourse(new Course(10001, "Testing data save"));
	}

}
