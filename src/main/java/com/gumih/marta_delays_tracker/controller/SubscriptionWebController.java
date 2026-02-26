package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.service.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SubscriptionWebController {

    private final SubscriptionService subscriptionService;

    public SubscriptionWebController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscriptions/{stationId}/subscribe")
    public String subscribe(@PathVariable Long stationId) {
        subscriptionService.subscribeCurrentUser(stationId);
        return "redirect:/dashboard";
    }

    @PostMapping("/subscriptions/{stationId}/unsubscribe")
    public String unsubscribe(@PathVariable Long stationId) {
        subscriptionService.unsubscribeCurrentUser(stationId);
        return "redirect:/dashboard";
    }
}