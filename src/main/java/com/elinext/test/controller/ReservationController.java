package com.elinext.test.controller;

import com.elinext.test.domain.Reservation;
import com.elinext.test.domain.Room;
import com.elinext.test.domain.User;
import com.elinext.test.service.impl.ReservationServiceImpl;
import com.elinext.test.service.impl.RoomServiceImpl;
import com.elinext.test.util.TaskUtil;
import com.elinext.test.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;

@Controller
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    private final RoomServiceImpl roomService;

    private final TimeUtil timeUtil;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getReservations(@AuthenticationPrincipal User user,
                                  Model model) {
        model.addAttribute("user", user);
        model.addAttribute("reservations", reservationService.findAll());
        model.addAttribute("rooms", roomService.findAllNamesQuery());

        return "reservation";
    }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public String addReservation(
      @AuthenticationPrincipal User user,
      @RequestParam String roomName,
      @RequestParam String operation,
      @RequestParam String operationDescription,
      @RequestParam String startTime,
      @RequestParam String endTime,
      Model model) {
    Time start = Time.valueOf(startTime + ":00");
    Time end = Time.valueOf(endTime + ":00");
    Time now = Time.valueOf(LocalTime.now());

    Room room = roomService.findByName(roomName);

    if (timeUtil.compareTime(start, end, reservationService.findSortedByUserId(user.getId()))) {
      if (timeUtil.compareTime(start, end, reservationService.findSortedByRoomId(room.getId()))) {

        Reservation reservation = new Reservation();

        reservation.setRoomId(room.getId());
        reservation.setUserId(user.getId());
        reservation.setOperation(operation);
        reservation.setOperationDescription(operationDescription);
        reservation.setStartTime(start);
        reservation.setEndTime(end);

        Reservation createdReservation = reservationService.save(reservation);

        long delay = end.getTime() - now.getTime();
        Timer timer = new Timer();
        TaskUtil taskUtil = new TaskUtil(reservationService);
        taskUtil.setId(createdReservation.getId());
        timer.schedule(taskUtil, delay);

        model.addAttribute("user", user);
        model.addAttribute("reservations", reservationService.findAll());
        model.addAttribute("rooms", roomService.findAllNamesQuery());
        return "reservation";
      }
    }
      model.addAttribute("user", user);
      model.addAttribute("reservations", reservationService.findAll());
      model.addAttribute("rooms", roomService.findAllNamesQuery());
      return "reservation";
    }


    @GetMapping("/delete")
    public String deleteReservation(@AuthenticationPrincipal User user,
                                    @RequestParam Long id,
                                    Model model) {
        reservationService.delete(id);

        model.addAttribute("reservations", reservationService.findByUserId(user.getId()));
        model.addAttribute("user", user);

        return "redirect:/user/profile";
    }
}
