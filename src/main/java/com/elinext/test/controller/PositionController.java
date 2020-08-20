package com.elinext.test.controller;

import com.elinext.test.domain.Position;
import com.elinext.test.domain.User;
import com.elinext.test.service.impl.PositionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionServiceImpl positionService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String getPositions(@AuthenticationPrincipal User user,
                               Model model) {
        List<Position> positions = positionService.findAll();

        model.addAttribute("positions", positions);
        return "positions";
    }

    @PostMapping
    public String createPosition(@AuthenticationPrincipal User user,
                                                   @RequestParam String positionName,
                                                   Model model) {
        Position position = new Position();
        position.setPositionName(positionName);

        positionService.save(position);

        List<Position> positions = positionService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("positions", positions);

        return "positions";
    }
}
