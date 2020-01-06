package com.home.realtor.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.home.realtor.models.Region;
import com.home.realtor.repositories.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService {
    final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region create(Region region) {
        return regionRepository.create(region);
    }

    @Override
    public Region update(Region region) {
        return regionRepository.update(region);
    }

    @Override
    public Region getById(String id) {
        return regionRepository.getById(id);
    }

    @Override
    public List<Region> findAllByCompanyId(String companyId) {
        return regionRepository.findAllByCompanyId(companyId);
    }

    @Override
    public void delete(String id) {
        regionRepository.delete(id);
    }

}
