package com.example.linkedout.service;

import com.example.linkedout.model.dtos.EmployeeDto;
import com.example.linkedout.model.entities.Company;
import com.example.linkedout.model.entities.Employee;
import com.example.linkedout.repository.CompanyRepository;
import com.example.linkedout.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }
    public void addEmployee(EmployeeDto employeeDto) {
        Employee byFirstName = this.employeeRepository
                .findByFirstName(employeeDto.getFirstName()).orElse(null);
        if (byFirstName != null) {
            throw new RuntimeException("Employee already exists");
        }
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        Company company = this.companyRepository.findById(employeeDto.getCompany()).orElse(null);
        if (company==null){
            throw new RuntimeException("Company does not exist.");
        }
        employee.setCompany(company);
        this.employeeRepository.save(employee);


    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> findById(String id) {
        return this.employeeRepository.findById(id);
    }
}
