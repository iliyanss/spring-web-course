package com.example.linkedout.repository;

import com.example.linkedout.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findByName(String name);
    List<Company>findAll();
    Optional<Company>findById(String id);
}
