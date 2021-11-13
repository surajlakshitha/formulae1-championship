package com.oop.coursework;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {

    static List<Formula1Driver> formula1DriverList = new ArrayList<>();
    static Scanner SCANNER = new Scanner(System.in);

    @Override
    public void createNewFormula1Driver() {
        System.out.println("createNewFormula1Driver");
    }

    @Override
    public void deleteExistingFormula1Driver() {
        System.out.println("deleteExistingFormula1Driver");
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
            for (Formula1Driver formula1Driver : formula1DriverList) {
                oos.writeObject(formula1Driver);
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
}
