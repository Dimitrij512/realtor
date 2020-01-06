package com.home.realtor.repositories;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import com.home.realtor.models.Company;
import com.home.realtor.models.Region;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RegionRepositoryImplTest {
    @Autowired
    MongoOperations regionOperation;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CompanyRepository companyRepository;

    Company preparedCompany;

    @BeforeEach
    void setUp() {
        preparedCompany = companyRepository.create(Company.builder().name("TestNameCompany").build());
    }

    @AfterEach
    void tearDown() {
        regionOperation.dropCollection(Region.class);
    }

    @Test
    void create() {
        Region preparedRegion = prepareRegion(preparedCompany.getId());
        Region savedRegion = regionRepository.create(preparedRegion);

        assertThat(preparedRegion).isEqualTo(savedRegion);
    }

    @Test
    void update() {
        Region savedRegion = regionRepository.create(prepareRegion(preparedCompany.getId()));

        savedRegion.setName("UpdatedName");
        Region updatedRegion = regionRepository.update(savedRegion);

        assertThat(savedRegion).isEqualTo(updatedRegion);
    }

    @Test
    void getById() {
        Region savedRegion = regionRepository.create(prepareRegion(preparedCompany.getId()));
        Region foundRegion = regionRepository.getById(savedRegion.getId());

        assertThat(savedRegion).isEqualTo(foundRegion);
    }

    @Test
    void findAllByCompanyId() {
        Region savedRegion = regionRepository.create(prepareRegion(preparedCompany.getId()));
        List<Region> foundRegions = regionRepository.findAllByCompanyId(savedRegion.getCompanyId());

        assertThat(foundRegions.size()).isEqualTo(1);
        assertThat(foundRegions.get(0)).isEqualTo(savedRegion);
    }

    @Test
    void delete() {
        Region savedRegion = regionRepository.create(prepareRegion(preparedCompany.getId()));
        regionRepository.delete(savedRegion.getId());

        List<Region> foundRegions = regionRepository.findAllByCompanyId(savedRegion.getCompanyId());
        assertThat(foundRegions.isEmpty()).isTrue();
    }

    private Region prepareRegion(String companyId) {
        Region region = new Region();
        region.setName("TestRegionName");
        region.setCompanyId(companyId);

        return region;
    }

}