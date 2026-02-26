package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.entity.Station;
import com.gumih.marta_delays_tracker.repository.StationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private StationRepository stationRepository;

    public StationController(StationRepository theStationRepository) {
        this.stationRepository = theStationRepository;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

}
