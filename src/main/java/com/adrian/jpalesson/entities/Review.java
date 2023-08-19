package com.adrian.jpalesson.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    @ManyToOne
    private Course course;

    public Review() {
    } 

    public Review(String description, ReviewRating rating) {
        this.description = description;
        this.rating = rating;
    }

    public Review(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", description=" + description + "]";
    }
    
}
