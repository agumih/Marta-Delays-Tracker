package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.dto.MartaRailArrival;
import com.gumih.marta_delays_tracker.entity.Station;
import com.gumih.marta_delays_tracker.entity.Subscription;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.repository.StationRepository;
import com.gumih.marta_delays_tracker.repository.SubscriptionRepository;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import com.gumih.marta_delays_tracker.service.MartaRailClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class DashboardController {

    private final UserRepository userRepository;
    private final StationRepository stationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final MartaRailClient martaRailClient;


    public DashboardController(UserRepository userRepository,
                               StationRepository stationRepository,
                               SubscriptionRepository subscriptionRepository,
                               MartaRailClient martaRailClient) {
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.martaRailClient = martaRailClient;

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found: " + email));

        List<Station> stations = stationRepository.findAllByActiveTrue();
        List<Subscription> subs = subscriptionRepository.findByUser(user);

        Set<Long> subscribedStationIds = subs.stream()
                .map(s -> s.getStation().getId())
                .collect(Collectors.toSet());

        // Build set of subscribed station codes (YOUR internal identifiers)
        Set<String> subscribedCodes = subs.stream()
                .map(s -> s.getStation().getCode())
                .collect(Collectors.toSet());

        // Fetch all arrivals once
        List<MartaRailArrival> allArrivals = martaRailClient.fetchArrivals();

        // Filter arrivals down to subscribed stations
        // Map: stationId -> list of arrivals
        java.util.Map<Long, List<MartaRailArrival>> arrivalsByStationId = new java.util.HashMap<>();

        // Build lookup: code -> stationId
        java.util.Map<String, Long> codeToId = subs.stream()
                .collect(Collectors.toMap(
                        s -> s.getStation().getCode(),
                        s -> s.getStation().getId(),
                        (a,b) -> a
                ));

        for (MartaRailArrival a : allArrivals) {
            String code = normalizeApiStationToCode(a.getStation());
            if (!subscribedCodes.contains(code)) continue;

            Long stationId = codeToId.get(code);
            if (stationId == null) continue;

            arrivalsByStationId
                    .computeIfAbsent(stationId, k -> new java.util.ArrayList<>())
                    .add(a);
        }

        model.addAttribute("user", user);
        model.addAttribute("stations", stations);
        model.addAttribute("subscriptions", subs);
        model.addAttribute("subscribedStationIds", subscribedStationIds);

        // NEW
        model.addAttribute("arrivalsByStationId", arrivalsByStationId);

        return "dashboard";
    }

    private static String normalizeApiStationToCode(String apiStation) {
        if (apiStation == null) return "";
        String s = apiStation.trim().toUpperCase();
        if (s.endsWith(" STATION")) {
            s = s.substring(0, s.length() - " STATION".length());
        }
        s = s.replace("/", " ");
        s = s.replace("-", " ");
        s = s.replace(".", " ");
        s = s.replaceAll("\\s+", " ").trim();
        s = s.replace(" ", "_");
        return s;
    }





//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Logged-in user not found: " + email));
//
//        List<Station> stations = stationRepository.findAllByActiveTrue();
//
//        List<Subscription> subs = subscriptionRepository.findByUser(user);
//
//        // For quick lookup in Thymeleaf: which station IDs are subscribed?
//        Set<Long> subscribedStationIds = subs.stream()
//                .map(s -> s.getStation().getId())
//                .collect(Collectors.toSet());
//
//        model.addAttribute("user", user);
//        model.addAttribute("stations", stations);
//        model.addAttribute("subscriptions", subs);
//        model.addAttribute("subscribedStationIds", subscribedStationIds);
//
//        return "dashboard";
//    }
}