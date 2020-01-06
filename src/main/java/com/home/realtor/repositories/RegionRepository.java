package com.home.realtor.repositories;

import java.util.List;

import com.home.realtor.models.Region;

public interface RegionRepository {
    Region create(Region region);
    Region update(Region region);
    Region getById(String id);
    List<Region> findAllByCompanyId (String companyId);
    void delete(final String id);
}
