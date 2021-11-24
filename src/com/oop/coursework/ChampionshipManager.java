package com.oop.coursework;

import java.util.List;
import java.util.Map;

public interface ChampionshipManager {
    void createNewFormula1Driver(Driver formula1Driver);
    void deleteExistingFormula1Driver(int driverId);
    void updateFormula1DriverTeam(int driverId, String teamName);
    Formula1Driver getFormula1DriverStatistics(int driverId);
    void updateRaceStats(Map<Integer, Integer> raceResult, Date date);
    void saveDriverLocal(String fileName);
    void retrieveDriverLocal(String fileName);
    void saveRaceLocal(String fileName);
    void retrieveRaceLocal(String fileName);
    boolean checkForDriverExist(int driverId);
    List<Formula1Driver> sortDriversByPoint ();
    List<Formula1Driver> sortDiversByNumberOfFirstPlaces ();
    List<Race> sortByDate();
    List<Race> filterByDriverId (String driverId);

}
