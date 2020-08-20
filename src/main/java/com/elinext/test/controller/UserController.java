package com.elinext.test.controller;

import com.elinext.test.domain.User;
import com.elinext.test.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final ReservationServiceImpl reservationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("reservations", reservationService.findByUserId(user.getId()));

        return "profile";
    }
}
