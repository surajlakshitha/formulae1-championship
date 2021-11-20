package com.oop.coursework;

import java.io.Serializable;
import java.util.Map;

//Assuming that one race can only participate 10 Formula Drivers
public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date date;
    private Map<Formula1Driver, Integer> driverPlaceMap;

    public Race(Date date, Map<Formula1Driver, Integer> driverPlaceMap) {
        this.date = date;
        this.driverPlaceMap = driverPlaceMap;
    }

    public Race() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Formula1Driver, Integer> getDriverPlaceMap() {
        return driverPlaceMap;
    }

    public void setDriverPlaceMap(Map<Formula1Driver, Integer> driverPlaceMap) {
        this.driverPlaceMap = driverPlaceMap;
    }
}