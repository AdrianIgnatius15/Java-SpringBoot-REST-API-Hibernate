package com.adrian.jpalesson.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(
        name = "STUDENT_COURSE",
        joinColumns = @JoinColumn(name = "STUDENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private List<Course> courses = new ArrayList<>();

    @Embedded
    private Address address;
    
    public Student() {
    }

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Student(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", passport=" + passport + "]";
    }
    
}
