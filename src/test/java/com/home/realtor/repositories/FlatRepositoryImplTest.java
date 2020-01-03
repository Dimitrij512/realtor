package com.home.realtor.repositories;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import com.home.realtor.models.Address;
import com.home.realtor.models.Company;
import com.home.realtor.models.Flat;
import com.home.realtor.models.Region;
import com.home.realtor.models.User;
import com.home.realtor.models.criteries.FlatCriteria;
import com.home.realtor.models.enums.Heating;
import com.home.realtor.models.enums.HotWater;
import com.home.realtor.models.enums.Role;
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
    private FlatRepositoryImpl flatRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    User preparedUser;
    Company preparedCompany;

    @BeforeEach
    void setUp() {
        preparedCompany = companyRepository.create(prepareCompany());
        preparedUser = userRepository.create(prepareUser(preparedCompany.getId(), "test@gmail.com"));
    }

    @AfterEach
    void tearDown() {
        operations.dropCollection(Flat.class);
        operations.dropCollection(User.class);
        operations.dropCollection(Company.class);
    }

    @Test
    void create() {
        Flat preparedFlat = prepareFlat(preparedCompany.getId());
        Flat createdFlat = flatRepository.create(preparedFlat);

        assertThat(preparedFlat).isEqualTo(createdFlat);
    }

    @Test
    void update() {
        Flat preparedFlat = prepareFlat(preparedCompany.getId());
        Flat createdFlat = flatRepository.create(preparedFlat);
        assertThat(preparedFlat).isEqualTo(createdFlat);

        createdFlat.setTypeRooms(TypeRooms.WALKABLE);
        Flat updatedFlat = flatRepository.update(createdFlat);
        assertThat(createdFlat).isEqualTo(updatedFlat);
    }

    @Test
    void getById() {
        Flat createdFlat = flatRepository.create(prepareFlat(preparedCompany.getId()));
        Flat foundFlat = flatRepository.getById(createdFlat.getId());

        assertThat(createdFlat).isEqualTo(foundFlat);
    }

    @Test
    void findAllByCompanyId() {
        Flat flat = flatRepository.create(prepareFlat(preparedCompany.getId()));
        List<Flat> flatList = flatRepository.findAllByCompanyId(flat.getCompanyId());

        assertThat(flatList.size()).isEqualTo(1);
    }

    @Test
    void findByCriteria() {
        Region region = new Region();
        region.setName("Каскад");
        Address address = new Address();
        address.setId("test");
        address.setStreet("test");
        address.setRegion(region);

        Flat preparedFlat = prepareFlat(preparedCompany.getId());
        preparedFlat.setAddress(address);

        Flat createdFlat = flatRepository.create(preparedFlat);

        FlatCriteria criteria = new FlatCriteria();
        criteria.setActive(true);
        criteria.setPrice(6000);
        criteria.setCompanyId(preparedCompany.getId());
        criteria.setStateList(Collections.singletonList(State.REPAIR));
        criteria.setHotWaterList(Collections.singletonList(HotWater.GAS_COLUMN));
        criteria.setHeatingList(Collections.singletonList(Heating.GENERAL));
        criteria.setTypeBuildingList(Collections.singletonList(TypeBuilding.NEW));
        criteria.setTypeFurnitureList(Collections.singletonList(TypeFurniture.NEW));
        criteria.setTypeRoomsList(Collections.singletonList(TypeRooms.DIVIDED));
        criteria.setRegionNameList(Collections.singletonList(region.getName()));

        List<Flat> byCriteria = flatRepository.findByCriteria(criteria);

        assertThat(byCriteria.isEmpty()).isFalse();
        assertThat(byCriteria.get(0)).isEqualTo(createdFlat);
    }

    @Test
    void delete() {
        Flat createdFlat = flatRepository.create(prepareFlat(preparedCompany.getId()));
        List<Flat> flatList = flatRepository.findAllByCompanyId(preparedCompany.getId());
        assertThat(flatList.size()).isEqualTo(1);

        flatRepository.delete(createdFlat.getId());
        flatList = flatRepository.findAllByCompanyId(preparedCompany.getId());
        assertThat(flatList.size()).isEqualTo(0);
    }

    private Flat prepareFlat(String companyId) {
        Flat flat = new Flat();
        flat.setActive(true);
        flat.setCompanyId(companyId);
        flat.setPrice(4000);
        flat.setHeating(Heating.GENERAL);
        flat.setState(State.REPAIR);
        flat.setHotWater(HotWater.GAS_COLUMN);
        flat.setTypeBuilding(TypeBuilding.NEW);
        flat.setTypeFurniture(TypeFurniture.NEW);
        flat.setTypeRooms(TypeRooms.DIVIDED);

        return flat;
    }

    private User prepareUser(String companyId, String email) {
        User user = new User();
        user.setEmail(email);
        user.setCompanyId(companyId);
        user.setName("TestName");
        user.setSureName("TestSurName");
        user.setRole(Role.ADMIN);

        return user;
    }

    private Company prepareCompany() {
        Company company = new Company();
        company.setName("TestCompany");

        return company;
    }

}