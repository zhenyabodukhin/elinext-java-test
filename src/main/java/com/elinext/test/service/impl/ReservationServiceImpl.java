package com.elinext.test.service.impl;

import com.elinext.test.domain.Reservation;
import com.elinext.test.exception.EntityNotFoundException;
import com.elinext.test.repository.ReservationRepository;
import com.elinext.test.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Transactional
    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    @Override
    public Reservation update(Reservation reservation) {
        return reservationRepository.saveAndFlush(reservation);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(reservationRepository.findById(id).isPresent()) {
            reservationRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Reservation.class, id);
        }
    }

    @Override
    public Reservation findById(Long id) {
        Optional<Reservation> result = reservationRepository.findById(id);
        if(result.isPresent()) {
            return  result.get();
        } else {
            throw new EntityNotFoundException(Reservation.class, id);
        }
    }

    @Override
    public List<Reservation> findByRoomId(Long id) {
        return reservationRepository.findByRoomId(id);
    }

    @Override
    public List<Reservation> findByUserId(Long id) {
        return reservationRepository.findByUserId(id);
    }
}
