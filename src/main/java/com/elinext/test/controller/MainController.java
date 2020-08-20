package com.elinext.test.controller;

import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final UserServiceImpl userService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("users", userService.findAll());
        return "main";
    }
}
