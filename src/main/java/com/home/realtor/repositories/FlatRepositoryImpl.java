package com.home.realtor.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.Flat;
import com.home.realtor.models.criteries.FlatCriteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

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
    public List<Flat> findAllByCompanyId(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(id));

        return operations.find(query, Flat.class);
    }

    @Override
    public List<Flat> findByCriteria(FlatCriteria criteria) {

        Aggregation agg = newAggregation(
                match(Criteria.where("active").is(criteria.isActive())
                        .and("companyId").is(criteria.getCompanyId())
                        .and("price").lte(criteria.getPrice())
                        .and("state").in(criteria.getStateList())
                        .and("heating").in(criteria.getHeatingList())
                        .and("hotWater").in(criteria.getHotWaterList())
                        .and("typeRooms").in(criteria.getTypeRoomsList())
                        .and("typeBuilding").in(criteria.getTypeBuildingList())
                        .and("typeFurniture").in(criteria.getTypeFurnitureList())),
            unwind("address.region"),
            match(Criteria.where("address.region.name").in(criteria.getRegionList()))
        );

        return operations.aggregate(agg, "flat", Flat.class).getMappedResults();
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Flat.class);
    }
}
