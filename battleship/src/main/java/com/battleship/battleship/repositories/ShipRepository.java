package com.battleship.battleship.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battleship.battleship.models.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long>{
    Optional<Ship> findByName(String name);
    List<Ship> findByUserId(long owner_id);
    List<Ship> findByUserIdNot(long owner_id);
    List<Ship> findAllByOrderByHealthDescPowerAscNameDesc();
}
