package com.gumih.marta_delays_tracker.repository;

import com.gumih.marta_delays_tracker.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findByCode(String code);
    List<Station> findAllByActiveTrue();
}
