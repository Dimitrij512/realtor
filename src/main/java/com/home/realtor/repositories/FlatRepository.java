package com.home.realtor.repositories;

import java.util.List;

import com.home.realtor.models.Flat;

public interface FlatRepository {
    Flat create(Flat flat);
    Flat update(Flat flat);
    Flat getById(String id);
    List<Flat> findAll();
    void delete(final String id);
}
