package com.home.realtor.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.FinderOfFlat;

@Repository
public class FinderOfFlatRepositoryImpl implements FinderOfFlatRepository {

    private final MongoOperations operations;

    @Autowired
    public FinderOfFlatRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public FinderOfFlat create(FinderOfFlat finderOfFlat) {
        return operations.insert(finderOfFlat);
    }

    @Override
    public FinderOfFlat update(FinderOfFlat finderOfFlat) {
        return operations.save(finderOfFlat);
    }

    @Override
    public FinderOfFlat getById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, FinderOfFlat.class);
    }

    @Override
    public List<FinderOfFlat> findAll() {
        return operations.findAll(FinderOfFlat.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, FinderOfFlat.class);
    }

}
