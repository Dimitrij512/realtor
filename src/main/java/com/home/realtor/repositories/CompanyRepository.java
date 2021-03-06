package com.home.realtor.repositories;

import com.home.realtor.models.Company;

public interface CompanyRepository {
    Company create(Company company);

    Company update(Company company);

    Company getById(String id);
}
