package com.home.realtor.repositories;

import java.util.List;

import com.home.realtor.models.FinderOfFlat;

public interface FinderOfFlatRepository {
    FinderOfFlat create(FinderOfFlat finderOfFlat);
    FinderOfFlat update(FinderOfFlat finderOfFlat);
    FinderOfFlat getById(String id);
    List<FinderOfFlat> findAll();
    void delete(final String id);
}
