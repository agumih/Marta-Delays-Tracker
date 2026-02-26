package com.gumih.marta_delays_tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MartaRailArrival {

    @JsonProperty("STATION")
    private String station;

    @JsonProperty("DESTINATION")
    private String destination;

    @JsonProperty("LINE")
    private String line;

    @JsonProperty("WAITING_TIME")
    private String waitingTime;

    @JsonProperty("WAITING_SECONDS")
    private String waitingSeconds;

    @JsonProperty("DELAY")
    private String delay;

    public String getStation() { return station; }
    public String getDestination() { return destination; }
    public String getLine() { return line; }
    public String getWaitingTime() { return waitingTime; }
    public String getWaitingSeconds() { return waitingSeconds; }
    public String getDelay() { return delay; }
}