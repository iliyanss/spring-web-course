package com.example.linkedout.web;

import com.example.linkedout.model.dtos.CompanyDto;
import com.example.linkedout.model.entities.Company;
import com.example.linkedout.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ModelAttribute("companyDto")
    public CompanyDto companyDto() {
        return new CompanyDto();
    }

    @ModelAttribute("companies")
    public List<Company> companies() {
        return this.companyService.getAllCompanies();
    }

    @GetMapping("/companies/add")
    public String addCompany() {
        return "company-add";
    }

    @PostMapping("/companies/add")
    public String addCompany(@Valid CompanyDto companyDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyDto", companyDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyDto", bindingResult);
            return "redirect:/companies/add";
        }
        this.companyService.addCompany(companyDto);
        return "redirect:/";
    }

    @GetMapping("/companies/all")
    public String allCompanies() {
        return "company-all";
    }



    @GetMapping("/company-details/{id}")
    public String showCompanyDetails(@PathVariable String id, Model model) {
        Company company = companyService.findById(id).orElse(null);
        model.addAttribute("company", company);
        return "company-details";
    }
}
