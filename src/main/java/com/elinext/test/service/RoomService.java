package com.elinext.test.service;

import com.elinext.test.domain.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAll();

    Room save (Room room);

    Room update (Room room);

    void delete (Long id);

    Room findById(Long id);

    List<String> findAllNamesQuery();

    Room findByName(String name);
}
