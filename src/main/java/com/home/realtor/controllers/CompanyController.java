package com.home.realtor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.realtor.models.Company;
import com.home.realtor.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    Company create(@RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping
    Company update(@RequestBody Company company) {
        return companyService.update(company);
    }

    @GetMapping("/id/{id}")
    Company getById(@PathVariable String id){
       return companyService.getById(id);
    }

}
