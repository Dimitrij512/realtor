package com.home.realtor.services;

import java.util.List;

import com.home.realtor.models.Region;

public interface RegionService {
    Region create(Region region);

    Region update(Region region);

    Region getById(String id);

    List<Region> findAllByCompanyId(String companyId);

    void delete(final String id);
}
