package com.elinext.test.service;

import com.elinext.test.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save (User user);

    User update (User user);

    void delete (Long id);

    User findById(Long id);

    User findByName (String name);

    User findByPositionId (Long id);
}
