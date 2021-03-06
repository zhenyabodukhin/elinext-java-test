package com.elinext.test.repository;

import com.elinext.test.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select p from Position p where p.positionName = :positionName")
    Position findByName(@Param("positionName") String name);

    @Query("select p.positionName from Position p")
    List<String> findAllPositionsQuery();
}
