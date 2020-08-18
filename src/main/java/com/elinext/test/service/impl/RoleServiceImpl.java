package com.elinext.test.service.impl;

import com.elinext.test.domain.Role;
import com.elinext.test.exception.EntityNotFoundException;
import com.elinext.test.repository.RoleRepository;
import com.elinext.test.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Role.class, id);
        }
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> result = roleRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Role.class, id);
        }
    }

    @Override
    public List<Role> findByUserId(Long id) {
        return roleRepository.findByUserId(id);
    }
}
