package com.elinext.test.service.impl;

import com.elinext.test.domain.User;
import com.elinext.test.exception.EntityNotFoundException;
import com.elinext.test.repository.UserRepository;
import com.elinext.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByPositionId(Long id) {
        return userRepository.findByPositionId(id);
    }
}
