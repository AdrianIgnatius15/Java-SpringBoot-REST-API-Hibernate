package com.adrian.jpalesson.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;

// @Entity
// @Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "EmployeeType")
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    public Employee() {
    } 

    public Employee(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }

    
}
