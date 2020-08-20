package com.elinext.test.repository;

import com.elinext.test.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r.name from Room r")
    List<String> findAllNamesQuery();

    @Query("select r from Room r where r.name = :name")
    Room findByName(@Param("name") String name);
}
