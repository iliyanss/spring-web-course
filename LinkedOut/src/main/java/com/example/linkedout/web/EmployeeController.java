package com.example.linkedout.web;

import com.example.linkedout.model.dtos.EmployeeDto;
import com.example.linkedout.model.entities.Company;
import com.example.linkedout.model.entities.Employee;
import com.example.linkedout.service.CompanyService;
import com.example.linkedout.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @ModelAttribute("companies")
    public List<Company> companies() {
        return this.companyService.getAllCompanies();
    }

    @ModelAttribute("employeeDto")
    public EmployeeDto employeeDto() {
        return new EmployeeDto();
    }

    @ModelAttribute("employees")
    public List<Employee> employees() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/employees/add")
    public String addEmployee() {
        return "employee-add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid EmployeeDto employeeDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeDto", employeeDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeDto", bindingResult);
            return "redirect:/employees/add";
        }
        this.employeeService.addEmployee(employeeDto);
        return "redirect:/";
    }

    @GetMapping("/employees/all")
    public String allEmployees() {
        return "employee-all";
    }

    @GetMapping("/employee-details/{id}")
    public String showCompanyDetails(@PathVariable String id, Model model) {
        Employee employee = employeeService.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employee-details";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/all";
    }
}
