package com.home.realtor.repositories;

import com.home.realtor.models.Company;

public interface CompanyRepository {
    Company create(Company company);
    Company update(Company flat);
    Company getById(String id);
}
