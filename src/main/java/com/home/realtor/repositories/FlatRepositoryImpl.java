package com.home.realtor.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.Flat;

@Repository
public class FlatRepositoryImpl implements FlatRepository {

    private final MongoOperations operations;

    @Autowired
    public FlatRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Flat create(Flat flat) {
        return operations.insert(flat);
    }

    @Override
    public Flat update(Flat flat) {
        return operations.save(flat);
    }

    @Override
    public Flat getById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Flat.class);
    }

    @Override
    public List<Flat> findAll() {
        return operations.findAll(Flat.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Flat.class);
    }
}
