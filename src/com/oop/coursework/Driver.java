package com.oop.coursework;

import java.io.Serializable;

public abstract class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    private int driverId;
    private String name;
    private String location;
    private String team;

    public Driver() {
    }

    public Driver(int driverId, String name, String location, String team) {
        this.driverId = driverId;
        this.name = name;
        this.location = location;
        this.team = team;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
