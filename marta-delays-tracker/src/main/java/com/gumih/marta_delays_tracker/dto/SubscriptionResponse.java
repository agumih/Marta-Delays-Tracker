package com.gumih.marta_delays_tracker.dto;

import java.time.LocalDateTime;


public class SubscriptionResponse {

    private Long id;
    private Long stationId;
    private String stationName;
    private String stationCode;
    private boolean active;
    private LocalDateTime createdAt;

    public SubscriptionResponse(Long id, Long stationId, String stationName, String stationCode,
                                boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getStationId() { return stationId; }
    public String getStationName() { return stationName; }
    public String getStationCode() { return stationCode; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
