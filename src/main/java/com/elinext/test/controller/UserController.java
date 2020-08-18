package com.elinext.test.controller;

import com.elinext.test.domain.User;
import com.elinext.test.request.UserRequest;
import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserRequest request) {
        User user = userService.findById(userId);

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPositionId(request.getPositionId());

        return new ResponseEntity<>(userService.update(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {

        userService.delete(userId);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/search/name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserByName(String name) {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }
}
