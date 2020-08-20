package com.elinext.test.controller;

import com.elinext.test.domain.User;
import com.elinext.test.service.impl.ReservationServiceImpl;
import com.elinext.test.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    private final ReservationServiceImpl reservationService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("reservations", reservationService.findByUserId(user.getId()));

        return "profile";
    }
}
