package com.elinext.test.controller;

import com.elinext.test.domain.Role;
import com.elinext.test.domain.User;
import com.elinext.test.request.UserRequest;
import com.elinext.test.service.impl.RoleServiceImpl;
import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    private final RoleServiceImpl roleService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional(rollbackFor = {Exception.class})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest request) {
        User user = new User();

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPositionId(request.getPositionId());

        User createdUser = userServiceImpl.save(user);

        Role role = new Role();

        role.setUserId(createdUser.getId());
        role.setRoleName("ROLE_USER");

        roleService.save(role);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
