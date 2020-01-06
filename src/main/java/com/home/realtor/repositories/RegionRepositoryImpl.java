package com.home.realtor.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.Region;

@Repository
public class RegionRepositoryImpl implements RegionRepository {

    private final MongoOperations operations;

    public RegionRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public Region create(Region region) {
        return operations.insert(region);
    }

    @Override
    public Region update(Region region) {
        return operations.save(region);
    }

    @Override
    public Region getById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Region.class);
    }

    @Override
    public List<Region> findAllByCompanyId(String companyId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));

        return operations.find(query, Region.class);
    }

    @Override
    public void delete(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Region.class);
    }

}
