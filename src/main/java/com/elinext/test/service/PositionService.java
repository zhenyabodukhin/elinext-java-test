package com.elinext.test.service;

import com.elinext.test.domain.Position;

import java.util.List;

public interface PositionService {

  List<Position> findAll();

  Position save(Position position);

  Position update(Position position);

  void delete(Long id);

  Position findById(Long id);

  Position findByName(String name);

  List<String> findAllPositionsQuery();
}
