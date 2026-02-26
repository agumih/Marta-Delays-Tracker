package com.gumih.marta_delays_tracker.service.impl;

import com.gumih.marta_delays_tracker.dto.SubscriptionResponse;
import com.gumih.marta_delays_tracker.entity.Station;
import com.gumih.marta_delays_tracker.entity.Subscription;
import com.gumih.marta_delays_tracker.entity.User;
import com.gumih.marta_delays_tracker.exception.DuplicateResourceException;
import com.gumih.marta_delays_tracker.exception.ResourceNotFoundException;
import com.gumih.marta_delays_tracker.repository.StationRepository;
import com.gumih.marta_delays_tracker.repository.SubscriptionRepository;
import com.gumih.marta_delays_tracker.repository.UserRepository;
import com.gumih.marta_delays_tracker.service.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private UserRepository userRepository;

    private StationRepository stationRepository;


    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   UserRepository userRepository,
                                   StationRepository stationRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
    }



    @Override
    @Transactional
    public void subscribeCurrentUser(Long stationId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for session email=" + email));

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found: id=" + stationId));

        boolean exists = subscriptionRepository.existsByUserAndStation(user, station);
        if (exists) {
            throw new DuplicateResourceException("Already subscribed to stationId=" + stationId);
        }

        Subscription subscription = new Subscription(user, station);
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void unsubscribeCurrentUser(Long stationId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for session email=" + email));

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found: id=" + stationId));

        Subscription sub = subscriptionRepository.findByUserAndStation(user, station)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for stationId=" + stationId));

        subscriptionRepository.delete(sub);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> getCurrentUserSubscriptions() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for session email=" + email));

        return subscriptionRepository.findByUser(user).stream()
                .map(sub -> new SubscriptionResponse(
                        sub.getId(),
                        sub.getStation().getId(),
                        sub.getStation().getStationName(),
                        sub.getStation().getCode(),
                        sub.getStation().getActive(),
                        sub.getCreatedAt()
                ))
                .toList();
    }






//    @Override
//    @Transactional
//    public SubscriptionResponse subscribe(Long userId, Long stationId) {
//
//        // 1) Validate if user exists
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found: id=" + userId));
//
//        //Valid if station exists
//        Station station = stationRepository.findById(stationId)
//                .orElseThrow(() -> new ResourceNotFoundException("Station not found: id=" + stationId));
//
//
//        // 3) Prevent duplicates
//        boolean exists = subscriptionRepository.existsByUserIdAndStationId(userId, stationId);
//        if (exists) {
//            throw new DuplicateResourceException(
//                    "Subscription already exists for userId=" + userId + " and stationId=" + stationId);
//        }
//
//        // Create and Save the subscription
//        Subscription subscription = new Subscription(user, station);
//
//        //return subscriptionRepository.save(subscription);
//        Subscription saved = subscriptionRepository.save(subscription);
//
//        // Mapping entity to DTO
//        return new SubscriptionResponse(
//                saved.getId(),
//                saved.getStation().getId(),
//                saved.getStation().getStationName(),
//                saved.getStation().getCode(),
//                saved.getStation().getActive(),
//                saved.getCreatedAt());
//
//    }
//
//
//    @Override
//    @Transactional
//    public void unsubscribe(Long userId, Long stationId) {
//        Subscription sub = subscriptionRepository.findByUserIdAndStationId(userId, stationId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                "Subscription not found for userId=" + userId + " and stationId=" + stationId));
//
//        subscriptionRepository.delete((sub));
//
//
//    }


//    @Override
//    public List<Subscription> getUserSubscriptions(Long userId) {
//
//        return subscriptionRepository.findByUserId(userId);
//    }



//    public List<SubscriptionResponse> getUserSubscriptions(Long userId) {
//        return subscriptionRepository.findByUserId(userId).stream()
//                .map(sub -> new SubscriptionResponse(
//                        sub.getId(),
//                        sub.getStation().getId(),
//                        sub.getStation().getStationName(),
//                        sub.getStation().getCode(),
//                        sub.getStation().getActive(),
//                        sub.getCreatedAt()
//                ))
//                .toList();
//    }

}
