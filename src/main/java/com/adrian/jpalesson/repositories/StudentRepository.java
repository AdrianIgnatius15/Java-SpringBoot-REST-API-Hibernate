package com.adrian.jpalesson.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.entities.Passport;
import com.adrian.jpalesson.entities.Student;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    
    @Autowired
    EntityManager eManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //Select * from Student where id = 10001;
    public Student getStudentById(int id) {
        return eManager.find(Student.class, id);
    }

    //Delete Student where id = 10001
    public void deleteStudentById(int id) {
        Student Student = getStudentById(id);
        eManager.remove(Student);
    }

    public Student saveStudent(Student student) {
        if(student.getId() == 0) {
            eManager.persist(student);
        } else {
            eManager.merge(student);
        }

        return student;
    }

    public void playingWithEntityManager() {
        Student student1 = new Student("Student 1 just testing");
        eManager.persist(student1);
        Student Student2 = getStudentById(10001);
        Student2.setName("Student 1 updated");;
        // eManager.persist(Student2);
        // eManager.flush(); //This method always saves the data into database like .NET Core "SaveChanges();"

        // student1.setName("Student 1 just testing if it's tracking");
        // Student2.setName("Student 2 just testing if it's tracking");
        // eManager.refresh(student1); //resets student1 property values which has tracked to be changed to the database version.
        // eManager.flush();
        // eManager.clear();
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("I897987");
        eManager.persist(passport);
        
        Student student = new Student("Alex");
        student.setPassport(passport);
        eManager.persist(student);
    }

    public Student getStudentByIdWithPassport(int id) {
        Student student = eManager.find(Student.class, id);
        logger.info("Student details --> {}", student);
		logger.info("Passport details --> {}", student.getPassport());
        return student;
    }

    public Passport getPassportByIdWithStudent(int id) {
        Passport passport = eManager.find(Passport.class, id);
        logger.info("Passport --> {}", passport);
		logger.info("Student details --> {}", passport.getStudent());
        return passport;
    }

    public Student getStudentWithCourses(int id) {
        Student student = eManager.find(Student.class, id);
        logger.info("Student --> {}", student);
        logger.info("Course details --> {}", student.getCourses());

        return student;
    }

    public void createStudentWithCourseAndPassport(Student student, Course course, Passport passport) {
        //insert the new student, course and passport
        // eManager.persist(student);
        eManager.persist(passport);
        eManager.persist(course);

        student.addCourse(course);
        student.setPassport(passport);
        course.addStudent(student);

        //Save only the owning side for passport and course which is student
        eManager.persist(student);
    }
}
