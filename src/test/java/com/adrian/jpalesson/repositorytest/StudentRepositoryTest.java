package com.adrian.jpalesson.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.adrian.jpalesson.JpalessonApplication;
import com.adrian.jpalesson.entities.Address;
import com.adrian.jpalesson.entities.Passport;
import com.adrian.jpalesson.entities.Student;
import com.adrian.jpalesson.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = JpalessonApplication.class)
class StudentRepositoryTest {

	@Autowired
	StudentRepository studentRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	@Transactional
	void getStudentByIdUnitTest() {
		Student student = studentRepository.getStudentById(20001);
		student.setAddress(new Address("70", "Eco Ardence, Setia Alam", "Shah Alam"));
		studentRepository.saveStudent(student);

		logger.info("Student details --> {}", student);
		logger.info("Address details --> {}", student.getAddress());
		assertEquals("Adrian", student.getName());
	}

	@Test
	void getStudentByIdWithPassportUnitTesting() {
		Student student = studentRepository.getStudentByIdWithPassport(20003);
		assertEquals("Florence", student.getName());
	}

	@Test
	void getPassportByIdWithStudentUnitTesting() {
		Passport passport = studentRepository.getPassportByIdWithStudent(30003);
		assertEquals("Florence", passport.getStudent().getName());
	}
}
