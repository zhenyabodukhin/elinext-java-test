package com.elinext.test.controller;

import com.elinext.test.domain.Room;
import com.elinext.test.domain.User;
import com.elinext.test.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping
    public String getRooms(@AuthenticationPrincipal User user,
                           Model model) {
        List<Room> rooms = roomService.findAll();

        model.addAttribute("rooms", rooms);
        return "room";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRoom(@AuthenticationPrincipal User user,
                             @RequestParam String roomName,
                             @RequestParam String roomType,
                             Model model) {
        Room room = new Room();
        room.setName(roomName);
        room.setType(roomType);

        roomService.save(room);

        List<Room> rooms = roomService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("rooms", rooms);

        return "room";
    }
}
