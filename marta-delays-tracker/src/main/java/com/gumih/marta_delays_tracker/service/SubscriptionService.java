package com.gumih.marta_delays_tracker.service;

import com.gumih.marta_delays_tracker.dto.SubscriptionResponse;
import com.gumih.marta_delays_tracker.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    void subscribeCurrentUser(Long stationId);
    void unsubscribeCurrentUser(Long stationId);
    List<SubscriptionResponse> getCurrentUserSubscriptions();


    //SubscriptionResponse subscribe(Long userId, Long stationId);
    //void unsubscribe(Long userId, Long stationId);
    //List<SubscriptionResponse> getUserSubscriptions(Long userId);



}
