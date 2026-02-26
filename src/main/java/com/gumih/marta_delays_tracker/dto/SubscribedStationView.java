package com.gumih.marta_delays_tracker.dto;

import java.util.List;

public class SubscribedStationView {

    private Long stationId;
    private String stationName;
    private String stationCode;
    private List<MartaRailArrival> arrivals;

    public SubscribedStationView(Long stationId, String stationName, String stationCode, List<MartaRailArrival> arrivals) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.arrivals = arrivals;
    }

    public Long getStationId() { return stationId; }
    public String getStationName() { return stationName; }
    public String getStationCode() { return stationCode; }
    public List<MartaRailArrival> getArrivals() { return arrivals; }
}