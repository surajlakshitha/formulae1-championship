package com.oop.coursework;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

//Assuming that one race can only participate 10 Formula Drivers
public class Race implements Serializable, Comparable<Race> {

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

    public String getDateString(){ return date.toString();}

    public void setDateString(){ this.date = date;}

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Formula1Driver, Integer> getDriverPlaceMap() {
        return driverPlaceMap;
    }

    public void setDriverPlaceMap(Map<Formula1Driver, Integer> driverPlaceMap) {
        this.driverPlaceMap = driverPlaceMap;
    }

    @Override
    public int compareTo(Race o) {
        return covertCustomDateToDate(o.getDate()).compareTo(covertCustomDateToDate(this.getDate()));
    }

    private java.util.Date covertCustomDateToDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date dateObj = null;
        try {
            String tempStrDate = date.getMonth()+"/"+date.getDay()+"/"+date.getYear();
            dateObj = dateFormat.parse(tempStrDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObj;
    }
}