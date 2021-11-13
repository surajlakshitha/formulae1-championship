package com.oop.coursework;

public class Formula1Driver {
    private String name;
    private String location;
    private String team;
    private int numberOfFirstPlaces;
    private int numberOfSecondPlaces;
    private int numberOfThirdPlaces;
    private int numberOfPoints;
    private int numberOfRacesParticipated;

    /* Default Constructor */
    public Formula1Driver() {
    }

    /* Parameterized Constructor */
    public Formula1Driver(String name, String location, String team, int numberOfFirstPlaces, int numberOfSecondPlaces, int numberOfThirdPlaces, int numberOfPoints, int numberOfRacesParticipated) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.numberOfFirstPlaces = numberOfFirstPlaces;
        this.numberOfSecondPlaces = numberOfSecondPlaces;
        this.numberOfThirdPlaces = numberOfThirdPlaces;
        this.numberOfPoints = numberOfPoints;
        this.numberOfRacesParticipated = numberOfRacesParticipated;
    }

    /* Getters and Setters */
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
