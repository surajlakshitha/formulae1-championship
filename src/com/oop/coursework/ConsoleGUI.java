package com.oop.coursework;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleGUI {

    static Scanner SCANNER = new Scanner(System.in);
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

    public static void main(String[] args) {
        displayMenuLoop:

        // Creating WHILE loop to loop the program until exit by pressing "0"
        while (true) {
            getMenu();

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
        System.out.println("Press [0] - Exist");
    }

    private static void createNewFormula1Driver() {
        manager.createNewFormula1Driver();
    }

    private static void deleteExistingFormula1Driver() {
        manager.deleteExistingFormula1Driver();
    }

    private static void updateFormula1DriverTeam() {
        manager.updateFormula1DriverTeam();
    }

    private static void getFormula1DriverStatistics() {
        manager.getFormula1DriverStatistics();
    }

    private static void updateRaceStats() {
        manager.updateRaceStats();
    }
}
