package com.home.realtor.repositories;

import java.util.ArrayList;
import java.util.List;

import com.home.realtor.models.criteries.FlatCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.Flat;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

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
    public List<Flat> findByCriteria(FlatCriteria criteria) {

        Aggregation agg = newAggregation(
                match(Criteria.where("active").is(true)
                        .and("price").lte(criteria.getPrice())
                        .and("state").in(criteria.getStateList())
                        .and("heating").in(criteria.getHeatingList())
                        .and("hotWater").in(criteria.getHotWaterList())
                        .and("typeRooms").in(criteria.getTypeRoomsList())
                        .and("typeBuilding").in(criteria.getTypeBuildingList())
                        .and("typeFurniture").in(criteria.getTypeFurnitureList()))
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
