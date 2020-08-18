package com.elinext.test.service;

import com.elinext.test.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role save (Role role);

    Role update (Role role);

    void delete (Long id);

    Role findById(Long id);

    List<Role> findByUserId(Long id);
}
