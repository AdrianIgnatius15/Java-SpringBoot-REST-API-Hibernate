package com.adrian.jpalesson.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
    private BigDecimal hourlyWage;

    protected PartTimeEmployee() {}

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name); //Calls the constructor of "Employee" class
        this.hourlyWage = hourlyWage;
    }

}
