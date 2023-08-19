package com.adrian.jpalesson.repositorytest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.adrian.jpalesson.JpalessonApplication;
import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.repositories.CourseJpaRepository;

@SpringBootTest(classes = JpalessonApplication.class)
class CourseSpringDataRepositoryTest {
    @Autowired
	CourseJpaRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		logger.info("Course optional --> {}", courseOptional.isPresent());
		assertTrue(courseOptional.isPresent());
	}

	@Test
	void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		logger.info("Course optional --> {}", courseOptional.isPresent());
		assertFalse(courseOptional.isPresent());
	}

    @Test
    void sortCourses() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Sorted courses --> {}", repository.findAll(sort));
    }

    @Test
    void paginationCourse() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);

        logger.info("First page --> {}", firstPage);
        logger.info("First page content --> {}", firstPage.getContent());
        assertTrue(firstPage.hasContent());
    }

    @Test
    void matchNameLikeCourse() {
        List<Course> courses = repository.findCoursesWhereNameMatches("NET");
        logger.info("Matched name courses --> {}", courses);
        assertNotNull(courses);
    }
}
