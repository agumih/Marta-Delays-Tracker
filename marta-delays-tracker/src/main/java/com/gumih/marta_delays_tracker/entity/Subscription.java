package com.gumih.marta_delays_tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "station_id"})
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //Many subscriptions belong to one user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    //Many subscriptions belong to one station
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Subscription() {}

    public Subscription(User user, Station station) {
        this.user = user;
        this.station = station;
    }


    public Long getId(){
        return id;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Station getStation (){
        return station;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}
