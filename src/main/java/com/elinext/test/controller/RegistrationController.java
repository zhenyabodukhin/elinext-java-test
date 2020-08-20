package com.elinext.test.controller;

import com.elinext.test.domain.Role;
import com.elinext.test.domain.User;
import com.elinext.test.service.impl.PositionServiceImpl;
import com.elinext.test.service.impl.RoleServiceImpl;
import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    private final RoleServiceImpl roleService;

    private final PositionServiceImpl positionService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("positions", positionService.findAllPositionsQuery());
        return "registration";
    }

    @PostMapping
    public String createUser(User user,
                             @RequestParam String positionName,
                             Model model) {
        if(userServiceImpl.findByName(user.getUsername())!=null) {
            return "registration";
        }

        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPositionId(positionService.findByName(positionName).getId());

        User createdUser = userServiceImpl.save(user);

        Role role = new Role();

        role.setUserId(createdUser.getId());
        role.setRoleName("ROLE_USER");

        roleService.save(role);

        model.addAttribute("user", createdUser);
        return "redirect:/";
    }
}
