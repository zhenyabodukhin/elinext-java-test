package com.elinext.test.controller;

import com.elinext.test.domain.Reservation;
import com.elinext.test.request.ReservationRequest;
import com.elinext.test.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createPosition(@RequestBody @Valid ReservationRequest request) {
        Reservation reservation = new Reservation();

        reservation.setRoomId(request.getRoomId());
        reservation.setUserId(request.getUserId());
        reservation.setOperation(request.getOperation());
        reservation.setOperationDescription(request.getOperationDescription());
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());

        return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.CREATED);
    }
}
