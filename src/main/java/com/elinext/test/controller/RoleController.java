package com.elinext.test.controller;

import com.elinext.test.domain.Position;
import com.elinext.test.domain.Role;
import com.elinext.test.request.PositionRequest;
import com.elinext.test.request.RoleRequest;
import com.elinext.test.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleRequest request) {
        Role role = new Role();

        role.setRoleName(request.getRoleName());
        role.setUserId(request.getUserId());

        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }
}
