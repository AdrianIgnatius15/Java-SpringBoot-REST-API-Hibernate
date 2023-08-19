package com.adrian.jpalesson.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@SpringBootTest(classes = JpalessonApplication.class)
class CriteriaQueryTest {

	@Autowired
	EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void getAllCoursesCriteriaQuery_UnitTest() {
		//SELECT course FROM Course course;

		//Step 1: Create a Criteria Query using the features from EntityManager
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		//Step 2: Create a root where this root is the table we want to search
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		//Step 3: Define a predicate for the root

		//Step 4: Add the predicate in the root

		//Step 6: Extract the results by executing the CriteriaQuery in EntityManager
		List<Course> courses = entityManager.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Courses retrieve using CriteriaQuery --> {}", courses);
		assertNotNull(courses);
	}

	@Test
	void getAllCoursesCriteriaQuery_Where_UnitTest() {
		//SELECT course FROM Course course WHERE LIKE '%.NET Core';

		//Step 1: Create a Criteria Query using the features from EntityManager
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		//Step 2: Create a root where this root is the table we want to search
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		//Step 3: Define a predicate for the root
		Predicate likeNETCore = criteriaBuilder.like(courseRoot.get("name"),"%.NET Core");

		//Step 4: Add the predicate in the root
		criteriaQuery.where(likeNETCore);
		//Step 6: Extract the results by executing the CriteriaQuery in EntityManager
		List<Course> courses = entityManager.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Courses retrieve using CriteriaQuery --> {}", courses);
		assertNotNull(courses);
	}

	@Test
	void getAllCoursesCriteriaQuery_without_Students_UnitTest() {
		//SELECT course FROM Course course WHERE course.students is empty;

		//Step 1: Create a Criteria Query using the features from EntityManager
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		//Step 2: Create a root where this root is the table we want to search
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		//Step 3: Define a predicate for the root
		Predicate students = criteriaBuilder.isEmpty(courseRoot.get("students"));

		//Step 4: Add the predicate in the root
		criteriaQuery.where(students);
		//Step 6: Extract the results by executing the CriteriaQuery in EntityManager
		List<Course> courses = entityManager.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Courses retrieve using CriteriaQuery --> {}", courses);
		assertNotNull(courses);
	}

	@Test
	void getAllCoursesCriteriaQuery_Join_UnitTest() {
		//SELECT course FROM Course course JOIN course.students is empty;

		//Step 1: Create a Criteria Query using the features from EntityManager
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		//Step 2: Create a root where this root is the table we want to search
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		//Step 3: Define a predicate for the root
		Join<Object, Object> join = courseRoot.join("students");

		//Step 4: Add the predicate in the root
		// criteriaQuery.where(join);
		//Step 6: Extract the results by executing the CriteriaQuery in EntityManager
		List<Course> courses = entityManager.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Courses retrieve using CriteriaQuery --> {}", courses);
		assertNotNull(courses);
	}

	@Test
	void getAllCoursesCriteriaQuery_LeftJoin_UnitTest() {
		//SELECT course FROM Course course JOIN course.students is empty;

		//Step 1: Create a Criteria Query using the features from EntityManager
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		//Step 2: Create a root where this root is the table we want to search
		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		//Step 3: Define a predicate for the root
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		//Step 4: Add the predicate in the root
		// criteriaQuery.where(join);
		//Step 6: Extract the results by executing the CriteriaQuery in EntityManager
		List<Course> courses = entityManager.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Courses retrieve using CriteriaQuery --> {}", courses);
		assertNotNull(courses);
	}

}
