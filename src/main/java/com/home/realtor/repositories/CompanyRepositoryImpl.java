package com.home.realtor.repositories;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.Company;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    private final MongoOperations operations;

    public CompanyRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Company create(Company company) {
        return operations.insert(company);
    }

    @Override
    public Company update(Company flat) {
        return operations.save(flat);
    }

    @Override
    public Company getById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return operations.findOne(query, Company.class);
    }
}
