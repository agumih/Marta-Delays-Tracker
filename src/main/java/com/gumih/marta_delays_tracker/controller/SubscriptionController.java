package com.gumih.marta_delays_tracker.controller;

import com.gumih.marta_delays_tracker.dto.SubscriptionResponse;
import com.gumih.marta_delays_tracker.entity.Subscription;
import com.gumih.marta_delays_tracker.repository.SubscriptionRepository;
import com.gumih.marta_delays_tracker.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public void subscribe(@RequestParam Long stationId) {
        subscriptionService.subscribeCurrentUser(stationId);
    }

    @DeleteMapping
    public void unsubscribe(@RequestParam Long stationId) {
        subscriptionService.unsubscribeCurrentUser(stationId);
    }

    @GetMapping
    public List<SubscriptionResponse> mySubscriptions() {
        return subscriptionService.getCurrentUserSubscriptions();
    }
}








//@RestController
//@RequestMapping("/api/subscriptions")
//public class SubscriptionController {
//
//    private SubscriptionService subscriptionService;
//
//    public SubscriptionController(SubscriptionService subscriptionService) {
//        this.subscriptionService = subscriptionService;
//    }
//
////    @PostMapping
////    public SubscriptionResponse subscribe(@RequestParam Long userId, @RequestParam Long stationId) {
////        return subscriptionService.subscribeCurrentUser(stationId);
////    }
//
//    @PostMapping
//    public void subscribe(@RequestParam Long stationId) {
//        subscriptionService.subscribeCurrentUser(stationId);
//    }
//
//
//    @DeleteMapping
//    public void unsubscribe(@RequestParam Long userId, @RequestParam Long stationId) {
//        subscriptionService.unsubscribeCurrentUser(stationId);
//    }
//
//    @GetMapping
//    public List<SubscriptionResponse> getUserSubscriptions(@RequestParam Long userId) {
//        return subscriptionService.getUserSubscriptions(userId);
//    }
//
//}
