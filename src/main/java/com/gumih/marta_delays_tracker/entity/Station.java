package com.gumih.marta_delays_tracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name="stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="station_name", nullable = false)
    private String stationName;


    //Station unique identifier
    @Column(name="station_identifier", nullable = false, unique = true)
    private String code;


    //if MARTA changes or retires a station
    @Column(name="active", nullable = false)
    private boolean active = true;

    public Station(){}

    public Station(String stationName, String code) {
        this.stationName = stationName;
        this.code = code;
    }

    public Station(String stationName, String code, boolean active) {
        this.stationName = stationName;
        this.code = code;
        this.active = active;
    }

    public Long getId(){
        return id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName (){
        return stationName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode (){
        return code;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive(){
        return active;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", code='" + code + '\'' +
                ", active=" + active +
                '}';
    }
}
