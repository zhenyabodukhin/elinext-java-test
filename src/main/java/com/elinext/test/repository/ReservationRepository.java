package com.elinext.test.repository;

import com.elinext.test.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.roomId = :roomId")
    List<Reservation> findByRoomId(@Param("roomId") Long id);

    @Query("select r from Reservation r where r.userId = :userId")
    List<Reservation> findByUserId(@Param("userId")Long id);
}
