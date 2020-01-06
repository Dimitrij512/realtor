package com.home.realtor.services;

import com.home.realtor.models.Company;

public interface CompanyService {
    Company create(Company company);

    Company update(Company company);

    Company getById(String id);
}
