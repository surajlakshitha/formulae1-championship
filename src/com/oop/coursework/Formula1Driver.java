package com.oop.coursework;

import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numberOfFirstPlaces;
    private int numberOfSecondPlaces;
    private int numberOfThirdPlaces;
    private int numberOfPoints;
    private int numberOfRacesParticipated;

    public Formula1Driver(String name, String location, String team, int numberOfFirstPlaces, int numberOfSecondPlaces, int numberOfThirdPlaces, int numberOfPoints, int numberOfRacesParticipated) {
        super(name, location, team);
        this.numberOfFirstPlaces = numberOfFirstPlaces;
        this.numberOfSecondPlaces = numberOfSecondPlaces;
        this.numberOfThirdPlaces = numberOfThirdPlaces;
        this.numberOfPoints = numberOfPoints;
        this.numberOfRacesParticipated = numberOfRacesParticipated;
    }

    public int getNumberOfFirstPlaces() {
        return numberOfFirstPlaces;
    }

    public void setNumberOfFirstPlaces(int numberOfFirstPlaces) {
        this.numberOfFirstPlaces = numberOfFirstPlaces;
    }

    public int getNumberOfSecondPlaces() {
        return numberOfSecondPlaces;
    }

    public void setNumberOfSecondPlaces(int numberOfSecondPlaces) {
        this.numberOfSecondPlaces = numberOfSecondPlaces;
    }

    public int getNumberOfThirdPlaces() {
        return numberOfThirdPlaces;
    }

    public void setNumberOfThirdPlaces(int numberOfThirdPlaces) {
        this.numberOfThirdPlaces = numberOfThirdPlaces;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfRacesParticipated() {
        return numberOfRacesParticipated;
    }

    public void setNumberOfRacesParticipated(int numberOfRacesParticipated) {
        this.numberOfRacesParticipated = numberOfRacesParticipated;
    }
}
