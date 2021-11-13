package com.oop.coursework;

import java.util.List;

public interface ChampionshipManager {
    void createNewFormula1Driver();
    void deleteExistingFormula1Driver();
    void updateFormula1DriverTeam();
    void getFormula1DriverStatistics();
    void updateRaceStats();
    void saveFormula1DriverToFile();
    void retrieveFormula1DriverFromFile();
}
