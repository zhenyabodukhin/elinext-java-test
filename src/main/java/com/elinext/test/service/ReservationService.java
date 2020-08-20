package com.elinext.test.service;

import com.elinext.test.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation save (Reservation reservation);

    Reservation update (Reservation reservation);

    void delete (Long id);

    Reservation findById(Long id);

    List<Reservation> findByRoomId(Long id);

    List<Reservation> findByUserId(Long id);

    List<Reservation> findSortedByUserId(Long id);

    List<Reservation> findSortedByRoomId(Long id);
}
