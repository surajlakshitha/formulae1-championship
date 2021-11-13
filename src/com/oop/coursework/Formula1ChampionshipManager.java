package com.oop.coursework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Formula1ChampionshipManager implements ChampionshipManager {

    static List<Formula1Driver> formula1DriverList = new ArrayList<>();

    @Override
    public void createNewFormula1Driver(Driver formula1Driver) {
        System.out.println("createNewFormula1Driver");
        formula1DriverList.add((Formula1Driver) formula1Driver);
        saveFormula1DriverToFile();
    }

    @Override
    public void deleteExistingFormula1Driver(int driverId) {
        System.out.println("deleteExistingFormula1Driver");
        boolean isFound = false;
        // Find Object from the List
        List<Driver> checkForDriver = formula1DriverList.stream().filter(driver -> driver.getDriverId() == (driverId)).collect(Collectors.toList());

        for (Driver driver : checkForDriver) {
            if (driver.getDriverId() == driverId) {
                isFound = true;
                formula1DriverList.remove(driver);
            }
        }

        if (!isFound) {
            System.out.println("Invalid Driver Id");
        }
        saveFormula1DriverToFile();
    }

    @Override
    public void updateFormula1DriverTeam(int driverId ,String newTeam) {
        System.out.println("updateFormula1DriverTeam");

        List<Formula1Driver> checkForDriver = formula1DriverList.stream().filter(driver -> driver.getDriverId() == (driverId)).collect(Collectors.toList());

        for (Formula1Driver driver : checkForDriver) {
            if (driver.getDriverId() == driverId) {
                Formula1Driver updatedDriver = driver;
                updatedDriver.setTeam(newTeam);
                formula1DriverList.set(formula1DriverList.indexOf(driver), updatedDriver);
            }
        }
        saveFormula1DriverToFile();
    }

    @Override
    public Formula1Driver getFormula1DriverStatistics(int driverId) {
        System.out.println("getFormula1DriverStatistics");

        Formula1Driver foundDriver = null;
        // Find Object from the List
        List<Driver> checkForDriver = formula1DriverList.stream().filter(driver -> driver.getDriverId() == (driverId)).collect(Collectors.toList());
        if (checkForDriver.size() == 0) {
            System.out.println("Invalid Driver Id");
        } else {
            foundDriver = (Formula1Driver) checkForDriver.get(0); // TODO
        }
        return foundDriver;
    }

    @Override
    public void updateRaceStats() {
        System.out.println("updateRaceStats");
    }

    @Override
    public void saveFormula1DriverToFile() {
        System.out.println("saveFormula1DriverToFile");
        try {
            FileOutputStream fos = new FileOutputStream("DriverDetails.txt", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Formula1Driver driver : formula1DriverList) {
                oos.writeObject(driver);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveFormula1DriverFromFile() {
        System.out.println("retrieveFormula1DriverFromFile");
        try {
            FileInputStream fis = new FileInputStream("DriverDetails.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (;;) {
                try {
                    Formula1Driver formula1Driver = (Formula1Driver) ois.readObject();
                    formula1DriverList.add(formula1Driver);
                }catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkForDriverExist(int driverId) {
        List<Driver> checkForDriver = formula1DriverList
                .stream()
                .filter(driver -> driver.getDriverId() == (driverId))
                .collect(Collectors.toList());
        return checkForDriver.size() != 0;
    }
}
