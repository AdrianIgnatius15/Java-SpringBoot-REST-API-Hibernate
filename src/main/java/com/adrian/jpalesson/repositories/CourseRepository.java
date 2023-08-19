package com.adrian.jpalesson.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.jpalesson.entities.Course;
import com.adrian.jpalesson.entities.Review;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {
    
    @Autowired
    EntityManager eManager;

    //Select * from Course where id = 10001;
    public Course getCourseById(int id) {
        return eManager.find(Course.class, id);
    }

    //Delete course where id = 10001
    public void deleteCourseById(int id) {
        Course course = getCourseById(id);
        eManager.remove(course);
    }

    public Course saveCourse(Course course) {
        if(course.getId() == 0) {
            eManager.persist(course);
        } else {
            eManager.merge(course);
        }

        return course;
    }

    public void playingWithEntityManager() {
        Course course1 = new Course("Course 1 just testing");
        eManager.persist(course1);
        Course course2 = getCourseById(10001);
        course2.setName("Course 1 updated");;
        // eManager.persist(course2);
        // eManager.flush(); //This method always saves the data into database like .NET Core "SaveChanges();"

        // course1.setName("Course 1 just testing if it's tracking");
        // course2.setName("Course 2 just testing if it's tracking");
        // eManager.refresh(course1); //resets course1 property values which has tracked to be changed to the database version.
        // eManager.flush();
        // eManager.clear();
    }

    //Many to one, one to many relationship example method.
    public void addReviewsToCourse(int courseId, List<Review> reviews) {
        Course course = getCourseById(courseId);
        for(Review review : reviews) {
            //set the relationship between "Course" and "Review"
            course.addReview(review);
            review.setCourse(course);

            //save "Review" to the database
            eManager.persist(review);
        }
    }
}
