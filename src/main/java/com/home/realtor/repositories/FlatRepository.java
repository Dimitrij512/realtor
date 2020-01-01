package com.home.realtor.repositories;

import java.util.List;

import com.home.realtor.models.Flat;
import com.home.realtor.models.criteries.FlatCriteria;

public interface FlatRepository {
    Flat create(Flat flat);
    Flat update(Flat flat);
    Flat getById(String id);
    List<Flat> findAll();
    List<Flat> findByCriteria(FlatCriteria criteria);
    void delete(final String id);
}
