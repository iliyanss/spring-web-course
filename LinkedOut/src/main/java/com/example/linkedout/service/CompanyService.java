package com.example.linkedout.service;

import com.example.linkedout.model.dtos.CompanyDto;
import com.example.linkedout.model.entities.Company;
import com.example.linkedout.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    public void addCompany(CompanyDto companyDto) {
        Company byName = this.companyRepository.findByName(companyDto.getName()).orElse(null);
        if (byName != null) {
            throw new RuntimeException("Company with name " + companyDto.getName() + " already exists");
        }
        Company company = modelMapper.map(companyDto, Company.class);
        this.companyRepository.save(company);
    }
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    public Optional<Company> findById(String id) {
        return this.companyRepository.findById(id);
    }
}
