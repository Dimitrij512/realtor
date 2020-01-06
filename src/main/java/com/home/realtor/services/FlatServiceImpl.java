package com.home.realtor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.home.realtor.models.Flat;
import com.home.realtor.models.criteries.FlatCriteria;
import com.home.realtor.repositories.FlatRepository;

@Service
public class FlatServiceImpl implements FlatService {
    final FlatRepository flatRepository;

    public FlatServiceImpl(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Override
    public Flat create(Flat flat) {
        return flatRepository.create(flat);
    }

    @Override
    public Flat update(Flat flat) {
        return flatRepository.update(flat);
    }

    @Override
    public Flat getById(String id) {
        return flatRepository.getById(id);
    }

    @Override
    public List<Flat> findAllByCompanyId(String id) {
        return flatRepository.findAllByCompanyId(id);
    }

    @Override
    public List<Flat> findByCriteria(FlatCriteria criteria) {
        return flatRepository.findByCriteria(criteria);
    }

    @Override
    public void delete(String id) {
        flatRepository.delete(id);
    }

}
