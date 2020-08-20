package com.elinext.test.schedule;

import com.elinext.test.exception.EntityNotFoundException;
import com.elinext.test.service.impl.ReservationServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
@Data
@RequiredArgsConstructor
public class Task extends TimerTask {

    private final ReservationServiceImpl reservationService;

    private Long id;

    @Override
    public void run() {
        try {
            reservationService.delete(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Бронирование завершено досрочно id помещения " + id);
        }

    }
}
