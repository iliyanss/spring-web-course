package com.example.pathfinder.repository;

import com.example.pathfinder.model.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE SIZE(r.comments) = (SELECT MAX(SIZE(r.comments)) FROM Route r)")
    Optional<Route> findMostCommented();

}
