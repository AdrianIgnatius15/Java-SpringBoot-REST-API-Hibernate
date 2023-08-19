package com.adrian.jpalesson.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adrian.jpalesson.entities.Course;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name LIKE %:name%")
    List<Course> findCoursesWhereNameMatches(@Param("name") String matchName);
}
