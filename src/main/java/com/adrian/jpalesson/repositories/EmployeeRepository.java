package com.adrian.jpalesson.repositories;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.adrian.jpalesson.entities.Employee;
import com.adrian.jpalesson.entities.FullTimeEmployee;
import com.adrian.jpalesson.entities.PartTimeEmployee;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class EmployeeRepository {
    
    @Autowired
    EntityManager eManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void insertEmployee(Employee employee) {
        eManager.persist(employee);
    }

    public List<Employee> getAllEmployees() {
        return eManager.createQuery("SELECT employee FROM Employee employee", Employee.class).getResultList();
    }

    public List<FullTimeEmployee> getAllFullTimeEmployees() {
        return eManager.createQuery("SELECT fullTimeEmployee FROM FullTimeEmployee fullTimeEmployee", FullTimeEmployee.class).getResultList();
    }

    public List<PartTimeEmployee> getAllPartTimeEmployees() {
        return eManager.createQuery("SELECT partTimeEmployee FROM PartTimeEmployee partTimeEmployee", PartTimeEmployee.class).getResultList();
    }
}
