package com.home.realtor.repositories;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.realtor.models.Address;
import com.home.realtor.models.Flat;
import com.home.realtor.models.Region;
import com.home.realtor.models.criteries.FlatCriteria;
import com.home.realtor.models.enums.Heating;
import com.home.realtor.models.enums.HotWater;
import com.home.realtor.models.enums.State;
import com.home.realtor.models.enums.TypeBuilding;
import com.home.realtor.models.enums.TypeFurniture;
import com.home.realtor.models.enums.TypeRooms;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FlatRepositoryImplTest {
    @Autowired
    private MongoOperations operations;

    @Autowired
    private FlatRepositoryImpl repository;


    @Test
    void create() {
        Flat preparedFlat = prepareFlat();
        Flat createdFlat = repository.create(preparedFlat);

        assertThat(preparedFlat).isEqualTo(createdFlat);
    }

    @Test
    void update() {
        Flat preparedFlat = prepareFlat();
        preparedFlat.setHotWater(HotWater.BOILER);
        Flat createdFlat = repository.create(preparedFlat);

        assertThat(preparedFlat).isEqualTo(createdFlat);
    }

    @Test
    void getById() {
        Flat createdFlat = repository.create(prepareFlat());
        Flat foundFlat = repository.getById(createdFlat.getId());

        assertThat(createdFlat).isEqualTo(foundFlat);
    }

    @Test
    void findAll() {
        repository.create(prepareFlat());
        List<Flat> flatList = repository.findAll();

        assertThat(flatList.size()).isEqualTo(1);
    }

    @Test
    void findByCriteria() throws JsonProcessingException {
        Region region = new Region();
        region.setName("Каскад");
        Address address = new Address();
        address.setId("test");
        address.setStreet("test");
        address.setRegion(region);

        Flat preparedFlat = prepareFlat();
        preparedFlat.setAddress(address);

        Flat createdFlat = repository.create(preparedFlat);

        FlatCriteria criteria = new FlatCriteria();
        criteria.setActive(true);
        criteria.setPrice(6000);
        criteria.setStateList(Arrays.asList(State.REPAIR));
        criteria.setHotWaterList(Arrays.asList(HotWater.GAS_COLUMN));
        criteria.setHeatingList(Arrays.asList(Heating.GENERAL));
        criteria.setTypeBuildingList(Arrays.asList(TypeBuilding.NEW));
        criteria.setTypeFurnitureList(Arrays.asList(TypeFurniture.NEW));
        criteria.setTypeRoomsList(Arrays.asList(TypeRooms.DIVIDED));
        criteria.setRegionNameList(Arrays.asList(region.getName()));

        List<Flat> byCriteria = repository.findByCriteria(criteria);

        assertThat(byCriteria.isEmpty()).isFalse();
        assertThat(byCriteria.get(0)).isEqualTo(createdFlat);
    }

    @Test
    void delete() {
        Flat createdFlat = repository.create(prepareFlat());
        List<Flat> flatList = repository.findAll();
        assertThat(flatList.size()).isEqualTo(1);

        repository.delete(createdFlat.getId());
        flatList = repository.findAll();
        assertThat(flatList.size()).isEqualTo(0);
    }

    private Flat prepareFlat() {
        Flat flat = new Flat();
        flat.setActive(true);
        flat.setPrice(4000);
        flat.setHeating(Heating.GENERAL);
        flat.setState(State.REPAIR);
        flat.setHotWater(HotWater.GAS_COLUMN);
        flat.setTypeBuilding(TypeBuilding.NEW);
        flat.setTypeFurniture(TypeFurniture.NEW);
        flat.setTypeRooms(TypeRooms.DIVIDED);

        return flat;
    }

}