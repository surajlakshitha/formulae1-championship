package com.oop.coursework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Formula1ChampionshipManager implements ChampionshipManager {

    static List<Driver> formula1DriverList = new ArrayList<>();
    static Scanner SCANNER = new Scanner(System.in);

    @Override
    public void createNewFormula1Driver(Driver formula1Driver) {
        System.out.println("createNewFormula1Driver");
        formula1DriverList.add(formula1Driver);
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
    public void updateFormula1DriverTeam() {
        System.out.println("updateFormula1DriverTeam");
    }

    @Override
    public void getFormula1DriverStatistics() {
        System.out.println("getFormula1DriverStatistics");
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
            for (Driver driver : formula1DriverList) {
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
                    Driver formula1Driver = (Driver) ois.readObject();
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
}
