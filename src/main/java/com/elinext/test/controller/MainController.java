package com.elinext.test.controller;

import com.elinext.test.domain.User;
import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final UserServiceImpl userService;

    @GetMapping("/")
    public String greeting(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "main";
    }
}
