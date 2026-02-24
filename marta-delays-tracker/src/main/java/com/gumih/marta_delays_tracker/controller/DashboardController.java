package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.entity.Station;
import com.gumih.marta_delays_tracker.entity.Subscription;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.repository.StationRepository;
import com.gumih.marta_delays_tracker.repository.SubscriptionRepository;
import com.gumih.marta_delays_tracker.repository.UserRepository;
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

    public DashboardController(UserRepository userRepository,
                               StationRepository stationRepository,
                               SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found: " + email));

        List<Station> stations = stationRepository.findAllByActiveTrue();

        List<Subscription> subs = subscriptionRepository.findByUser(user);

        // For quick lookup in Thymeleaf: which station IDs are subscribed?
        Set<Long> subscribedStationIds = subs.stream()
                .map(s -> s.getStation().getId())
                .collect(Collectors.toSet());

        model.addAttribute("user", user);
        model.addAttribute("stations", stations);
        model.addAttribute("subscriptions", subs);
        model.addAttribute("subscribedStationIds", subscribedStationIds);

        return "dashboard";
    }
}