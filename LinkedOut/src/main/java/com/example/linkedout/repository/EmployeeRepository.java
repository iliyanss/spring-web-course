package com.example.linkedout.repository;

import com.example.linkedout.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByFirstName(String firstName);
    void deleteById(String id);
}
