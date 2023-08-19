package com.adrian.jpalesson.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {
    private BigDecimal salary;

    protected FullTimeEmployee() {}

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name); //Calls the constructor of "Employee" class
        this.salary = salary;
    }

}
