package com.example.linkedout.model.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class EmployeeDto {

    @NotBlank
    @Size(min = 2)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    private String lastName;

    @NotBlank
    private String educationLevel;

    @NotBlank
    private String jobTitle;

    @NotBlank
    private String birthDate;

    @Positive
    private BigDecimal salary;

    private String company;

    public EmployeeDto() {

    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public EmployeeDto setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public EmployeeDto setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public EmployeeDto setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public EmployeeDto setCompany(String company) {
        this.company = company;
        return this;
    }
}
