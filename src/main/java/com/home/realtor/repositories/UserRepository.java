package com.home.realtor.repositories;

import java.util.List;

import com.home.realtor.models.User;

public interface UserRepository {
    User create(User user);
    User update(User user);
    User findByEmail (final String email);
    User getByCompanyIdAndUserId(final String companyId, final String userId);
    List<User> findAllByCompanyId(final String id);
    void delete(final String id);
}
