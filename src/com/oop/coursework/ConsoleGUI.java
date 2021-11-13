package com.oop.coursework;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleGUI {

    static Scanner SCANNER = new Scanner(System.in);
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

    public static void main(String[] args) {

        // Update List from File
        manager.retrieveFormula1DriverFromFile();

        displayMenuLoop:
        // Creating WHILE loop to loop the program until exit by pressing "0"

        while (true) {
            getMenu(); // Display Menu

            System.out.println("\nSelect Option to Continue => ");
            int selectOption;
            while (true) {
                try {
                    selectOption = SCANNER.nextInt();
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.print("Invalid Option ! ");
                    SCANNER.next();
                    continue;
                }
                break;
            }

            switch (selectOption){
                case 1 :
                    createNewFormula1Driver();
                    break;
                case 2 :
                    deleteExistingFormula1Driver();
                    break;
                case 3 :
                    updateFormula1DriverTeam();
                    break;
                case 4 :
                    getFormula1DriverStatistics();
                    break;
                case 5 :
                    updateRaceStats();
                    break;
                case 0 :
                    System.out.println("Thank you for using Formula1Manager Console GUI ! ");
                    break displayMenuLoop;
            }
        }
    }

    private static void getMenu() {
        System.out.println("<--- Welcome to Formula1Championship League --->");
        System.out.println("Press [1] - Create New Formula1Driver");
        System.out.println("Press [2] - Delete Existing Formula1Driver");
        System.out.println("Press [3] - Update Formula1Driver's Team");
        System.out.println("Press [4] - Get Formula1Driver Statistics");
        System.out.println("Press [5] - Update Race Stats");
        System.out.println("Press [0] - Exit");
    }

    private static void createNewFormula1Driver() {
        System.out.println("Enter Driver Id : ");
        int driverId = SCANNER.nextInt();

        System.out.println("Enter Driver Name : ");
        String driverName = SCANNER.next();

        System.out.println("Enter Driver Location : ");
        String driverLocation = SCANNER.next();

        System.out.println("Enter Team Name : ");
        String teamName = SCANNER.next();

        System.out.println("Enter Number of First Places : ");
        int numberOfFirstPlaces = SCANNER.nextInt();

        System.out.println("Enter Number of Second Places : ");
        int numberOfSecondPlaces = SCANNER.nextInt();

        System.out.println("Enter Number of Third Places : ");
        int numberOfThirdPlaces = SCANNER.nextInt();

        System.out.println("Enter Number of Points : ");
        int numberOfPoints = SCANNER.nextInt();

        System.out.println("Enter Number of Races Participated : ");
        int numberOfRacesParticipated = SCANNER.nextInt();

        Driver newDriver = new Formula1Driver(driverId, driverName, driverLocation, teamName, numberOfFirstPlaces, numberOfSecondPlaces, numberOfThirdPlaces, numberOfPoints, numberOfRacesParticipated);
        manager.createNewFormula1Driver(newDriver);
    }

    private static void deleteExistingFormula1Driver() {
        System.out.println("Enter Driver Id : ");
        int existingDriverId = SCANNER.nextInt();
        manager.deleteExistingFormula1Driver(existingDriverId);
    }

    private static void updateFormula1DriverTeam() {
        manager.updateFormula1DriverTeam();
    }

    private static void getFormula1DriverStatistics() {
        manager.getFormula1DriverStatistics();
        // For Debug
        for (Driver driver : manager.formula1DriverList) {
            System.out.println(driver.getName());
        }
    }

    private static void updateRaceStats() {
        manager.updateRaceStats();
    }
}
