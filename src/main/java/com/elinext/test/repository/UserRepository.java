package com.elinext.test.repository;

import com.elinext.test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login = :login")
    User findByName (@Param("login") String name);

    @Query("select u from User u where u.positionId = :positionId")
    User findByPositionId (@Param("positionId") Long id);
}
