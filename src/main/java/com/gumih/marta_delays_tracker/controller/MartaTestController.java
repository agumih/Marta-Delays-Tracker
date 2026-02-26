package com.gumih.marta_delays_tracker.controller;


import com.gumih.marta_delays_tracker.service.MartaRailClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marta")
public class MartaTestController {

    private final MartaRailClient client;

    public MartaTestController(MartaRailClient client) {
        this.client = client;
    }

    @GetMapping("/rail")
    public Object testRail() {
        return client.fetchArrivals();
    }
}