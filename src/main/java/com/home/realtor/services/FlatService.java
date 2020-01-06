package com.home.realtor.services;

import java.util.List;

import com.home.realtor.models.Flat;
import com.home.realtor.models.criteries.FlatCriteria;

public interface FlatService {
    Flat create(Flat flat);

    Flat update(Flat flat);

    Flat getById(String id);

    List<Flat> findAllByCompanyId(String id);

    List<Flat> findByCriteria(FlatCriteria criteria);

    void delete(final String id);
}
