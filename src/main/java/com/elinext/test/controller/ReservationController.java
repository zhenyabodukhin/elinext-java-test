package com.elinext.test.controller;

import com.elinext.test.domain.Reservation;
import com.elinext.test.domain.User;
import com.elinext.test.schedule.Task;
import com.elinext.test.service.impl.ReservationServiceImpl;
import com.elinext.test.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;

@Controller
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    private final RoomServiceImpl roomService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getReservations(@AuthenticationPrincipal User user,
                                  Model model) {
        List<Reservation> reservations = reservationService.findAll();
        List<String> rooms = roomService.findAllNamesQuery();

        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);
        model.addAttribute("rooms", rooms);

        return "reservation";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addReservation(@AuthenticationPrincipal User user,
                                  @RequestParam String roomName,
                                  @RequestParam String operation,
                                  @RequestParam String operationDescription,
                                  @RequestParam String startTime,
                                  @RequestParam String  endTime,
                                  Model model) {
        Reservation reservation = new Reservation();

        Time start = Time.valueOf(startTime + ":00");
        Time end = Time.valueOf(endTime + ":00");

        reservation.setRoomId(roomService.findByName(roomName).getId());
        reservation.setUserId(user.getId());
        reservation.setOperation(operation);
        reservation.setOperationDescription(operationDescription);
        reservation.setStartTime(start);
        reservation.setEndTime(end);

        Reservation createdReservation = reservationService.save(reservation);

        Time now = Time.valueOf(LocalTime.now());
        long delay = end.getTime() - now.getTime();
        Timer timer = new Timer();
        Task task = new Task(reservationService);
        task.setId(createdReservation.getId());
        timer.schedule(task, delay);


        List<Reservation> reservations = reservationService.findAll();
        List<String> rooms = roomService.findAllNamesQuery();

        model.addAttribute("user", user);
        model.addAttribute("reservations", reservations);
        model.addAttribute("rooms", rooms);

        return "reservation";
    }

    @GetMapping("/delete")
    public String deleteReservation(@AuthenticationPrincipal User user,
                                    @RequestParam Long id,
                                    Model model) {
        reservationService.delete(id);

        List<Reservation> reservations = reservationService.findByUserId(user.getId());

        model.addAttribute("reservations", reservations);
        model.addAttribute("user", user);

        return "redirect:/user/profile";
    }

}
