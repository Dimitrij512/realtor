package com.home.realtor.repositories;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import com.home.realtor.models.Company;
import com.home.realtor.models.User;
import com.home.realtor.models.enums.Role;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    private MongoOperations operations;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;


    User preparedUser;
    Company preparedCompany;

    @BeforeEach
    void setUp() {
        preparedCompany = companyRepository.create(prepareCompany());
        preparedUser = prepareUser(preparedCompany.getId(), "test@email.com");
    }

    @AfterEach
    void tearDown() {
        operations.dropCollection(User.class);
        operations.dropCollection(Company.class);
    }

    @Test
    void create() {
        User createdUser = userRepository.create(preparedUser);
        assertThat(preparedUser).isEqualTo(createdUser);
    }

    @Test
    void update() {
        User createdUser = userRepository.create(preparedUser);
        createdUser.setRole(Role.WORKER);

        User updatedUser = userRepository.update(createdUser);

        assertThat(createdUser).isEqualTo(updatedUser);
    }

    @Test
    void findByEmail() {
        User createdUser = userRepository.create(preparedUser);
        User foundUser = userRepository.findByEmail(createdUser.getEmail());

        assertThat(createdUser).isEqualTo(foundUser);
    }

    @Test
    void getByCompanyIdAndUserId() {
        User createdUser = userRepository.create(preparedUser);
        User foundUser = userRepository.getByCompanyIdAndUserId(createdUser.getCompanyId(), createdUser.getId());

        assertThat(createdUser).isEqualTo(foundUser);
    }

    @Test
    void findAllByCompanyId() {
        User createdUser = userRepository.create(preparedUser);
        List<User> userList = userRepository.findAllByCompanyId((createdUser.getCompanyId()));

        assertThat(userList.isEmpty()).isFalse();
        assertThat(createdUser).isEqualTo(userList.get(0));
    }

    @Test
    void delete() {
        User createdUser = userRepository.create(preparedUser);
        userRepository.delete(createdUser.getId());

        assertThat(userRepository.findByEmail(createdUser.getEmail())).isNull();
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
        return Company.builder().name("TestCompany").build();
    }
}