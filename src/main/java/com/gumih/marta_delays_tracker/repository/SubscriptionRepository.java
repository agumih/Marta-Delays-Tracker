package com.gumih.marta_delays_tracker.repository;

import com.gumih.marta_delays_tracker.entity.Station;
import com.gumih.marta_delays_tracker.entity.Subscription;
import com.gumih.marta_delays_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // For Thymeleaf, switching to quering by the User entity, instead of IDs.

    List<Subscription> findByUser(User user);

    boolean existsByUserAndStation(User user, Station station);

    Optional<Subscription> findByUserAndStation(User user, Station station);


//    List<Subscription> findByUserId(Long userId);
//
//    boolean existsByUserIdAndStationId(Long userId, Long stationId);
//
//    Optional<Subscription> findByUserIdAndStationId(Long userId, Long stationId);

}
