package com.home.realtor.services;

import org.springframework.stereotype.Service;

import com.home.realtor.models.Company;
import com.home.realtor.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
    final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) {
        return companyRepository.create(company);
    }

    @Override
    public Company update(Company flat) {
        return companyRepository.update(flat);
    }

    @Override
    public Company getById(String id) {
        return companyRepository.getById(id);
    }
}
