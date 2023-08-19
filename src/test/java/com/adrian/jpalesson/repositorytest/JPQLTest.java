package com.adrian.jpalesson.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adrian.jpalesson.JpalessonApplication;
import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.repositories.CourseRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest(classes = JpalessonApplication.class)
class JPQLTest {

	@Autowired
	EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void contextLoads() {
		logger.info("Testing is running	");
		// Course course = repository.getCourseById(10001);
		// assertEquals(".NET Core REST API with Kubernetes", course.getName());
		List courses = entityManager.createQuery("SELECT course FROM Course course").getResultList();
		logger.info("SELECT course FROM Course course --> {}", courses.toString());
	}

	@Test
	void getDataUsingJPQL_Typed() {
		List<Course> courses = entityManager.createQuery("SELECT course FROM Course course", Course.class).getResultList();
		logger.info("SELECT course FROM Course course --> {}", courses.toString());
	}

	@Test
	void jpql_searchCoursesWithoutStudents_unitTest() {
		List<Course> results = entityManager.createQuery("SELECT course FROM Course course WHERE course.students IS EMPTY", Course.class).getResultList();
		logger.info("Result of courses without students --> {}", results);
		assertEquals(1, results.size());
	}

	@Test
	@Transactional
	void jpql_searchCoursesWithAtLeast2Students_unitTest() {
		List<Course> results = entityManager.createQuery("SELECT s FROM Student s WHERE SIZE(s.courses) >= 2", Course.class).getResultList();
		logger.info("Result of courses with at least 2 students --> {}", results);
		assertEquals(1, results.size(), "Test is passed");
	}

	@Test
	@Transactional
	void jpql_searchCoursesWithAtLeast2StudentsAndOrderBy_unitTest() {
		List<Course> results = entityManager.createQuery("SELECT s FROM Student s WHERE order by SIZE(s.courses) >= 2", Course.class).getResultList();
		logger.info("Result of courses with at least 2 students --> {}", results);
		assertEquals(1, results.size(), "Test is passed");
	}

	@Test
	@Transactional
	void jpql_students_like_unitTest() {
		List<Course> results = entityManager.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%879%'", Course.class).getResultList();
		logger.info("Result of courses with at least 2 students --> {}", results);
		assertEquals(1, results.size(), "Test is passed");
	}

	@Test
	void jpql_join_unitTest() {
		List results = entityManager.createQuery("SELECT c, s FROM Course c JOIN c.students s").getResultList();
		for (Object result : results) {
			logger.info("Result of join --> {}", result.toString());
		}
	}

}
