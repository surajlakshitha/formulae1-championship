package com.oop.coursework;

public interface ChampionshipManager {
    void createNewFormula1Driver(Driver formula1Driver);
    void deleteExistingFormula1Driver(int driverId);
    void updateFormula1DriverTeam();
    Formula1Driver getFormula1DriverStatistics(int driverId);
    void updateRaceStats();
    void saveFormula1DriverToFile();
    void retrieveFormula1DriverFromFile();
}
