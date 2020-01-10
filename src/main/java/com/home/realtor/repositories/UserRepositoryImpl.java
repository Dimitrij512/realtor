package com.home.realtor.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.realtor.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final MongoOperations operations;

    public UserRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public User create(final User user) {
        return operations.insert(user);
    }

    @Override
    public User update(final User user) {
        return operations.save(user);
    }

    @Override
    public User findByEmail(final String email) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        return operations.findOne(query, User.class);
    }

    @Override
    public User getByCompanyIdAndUserId(final String companyId, final String userId) {
        final Query query = new Query();
        query.addCriteria(Criteria
            .where("companyId").is(companyId)
            .and("id").is(userId));

        return operations.findOne(query, User.class);
    }

    @Override
    public List<User> findAllByCompanyId(final String id) {
        return operations.findAll(User.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, User.class);
    }

}
