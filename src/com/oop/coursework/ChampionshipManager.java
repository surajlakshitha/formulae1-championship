package com.oop.coursework;

import java.util.List;
import java.util.Map;

public interface ChampionshipManager {
    void createNewFormula1Driver(Driver formula1Driver);
    void deleteExistingFormula1Driver(int driverId);
    void updateFormula1DriverTeam(int driverId, String teamName);
    Formula1Driver getFormula1DriverStatistics(int driverId);
    void updateRaceStats(Map<Integer, Integer> raceResult);
    void saveFormula1DriverToFile();
    void retrieveFormula1DriverFromFile();
    boolean checkForDriverExist(int driverId);
    List<Formula1Driver> sortDriversByPoint ();
}
