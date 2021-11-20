package com.oop.coursework;

import java.util.*;

public class ConsoleGUI {

    static Scanner SCANNER = new Scanner(System.in);
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
    static int[] daysInMonthForLeapYear = {31,29,31,30,31,30,31,31,30,31,30,31};
    static int[] daysInMonthForNotLeapYear = {31,28,31,30,31,30,31,31,30,31,30,31};


    public static void main(String[] args) {

        // Update List from File
        manager.retrieveDriverLocal(Formula1ChampionshipManager.driverFileName);
        manager.retrieveRaceLocal(Formula1ChampionshipManager.raceFileName);

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
                case 6 :
                    displayAll();
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
        System.out.println("Press [6] - Display All Drivers");
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
        System.out.println("Enter Driver ID : ");
        int driverId = SCANNER.nextInt();

        if(manager.checkForDriverExist(driverId)){
            System.out.println("Enter New Team Name : ");
            String newTeamName = SCANNER.next();
            manager.updateFormula1DriverTeam(driverId, newTeamName);
        }else{
            System.out.println("Driver not Found " + driverId);
        }
    }

    private static void getFormula1DriverStatistics() {
        System.out.println("Enter Driver Id : ");
        int existingDriverId = SCANNER.nextInt();
        Formula1Driver driver = manager.getFormula1DriverStatistics(existingDriverId);

        if (driver == null) {
            System.out.println("Driver Not Found!");
        } else {
            System.out.println("Driver Name : " + driver.getName());
            System.out.println("Driver Location : " + driver.getLocation());
            System.out.println("Driver Team : " + driver.getTeam());
            System.out.println("Number of First Places : " + driver.getNumberOfFirstPlaces());
            System.out.println("Number of Second Places : " + driver.getNumberOfSecondPlaces());
            System.out.println("Number of Third Places : " + driver.getNumberOfThirdPlaces());
            System.out.println("Number of Points : " + driver.getNumberOfPoints());
            System.out.println("Number of Races Participated  : " + driver.getNumberOfRacesParticipated());
        }
    }

    private static void updateRaceStats() {
        System.out.println("Enter Date : (YYYY-MM-DD)");
        String date =SCANNER.next();
        if (validateDate(date)) {
            //
            String [] dateArray= date.split("-");
            Date dateObj = new Date(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[0]));

            Map<Integer, Integer> raceResult = new HashMap<>();
            System.out.println("Enter Driver IDs (With comma separated)  : ");
            String driverIds = SCANNER.next();

            String[] idList = driverIds.split(",");
            for (String id : idList) {
                if (manager.checkForDriverExist(Integer.parseInt(id))){
                    System.out.println("Enter Place for the "+ id + " : ");
                    int point = SCANNER.nextInt();
                    raceResult.put(Integer.parseInt(id), point);
                }else {
                    System.out.println("Invalid Id...");
                }
            }
            manager.updateRaceStats(raceResult, dateObj);
        } else{
            System.out.println("Invalid Date...");
        }
    }

    private static void displayAll() {
        List<Formula1Driver> sortedFormula1DriverList = manager.sortDriversByPoint();

        // Convert List to 2D Array
        String[][] sortedList2d = new String[sortedFormula1DriverList.size()+1][9];
        String[] headers = new String[]{"Id", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "# of Points", "# of Races"};
        sortedList2d[0] = headers;

        for (int i = 0; i < sortedFormula1DriverList.size(); i++) {
            Formula1Driver formula1Driver = sortedFormula1DriverList.get(i);
            sortedList2d[i+1] = new String[]{
                    String.valueOf(formula1Driver.getDriverId()),
                    formula1Driver.getName(),
                    formula1Driver.getLocation(),
                    formula1Driver.getTeam(),
                    String.valueOf(formula1Driver.getNumberOfFirstPlaces()),
                    String.valueOf(formula1Driver.getNumberOfSecondPlaces()),
                    String.valueOf(formula1Driver.getNumberOfThirdPlaces()),
                    String.valueOf(formula1Driver.getNumberOfPoints()),
                    String.valueOf(formula1Driver.getNumberOfRacesParticipated())

            };
        }
        Map <Integer, Integer> columnLength = new HashMap<>();
        for (String[] strings : sortedList2d){
            for (int i=0; i<strings.length; i++){
                if (columnLength.get(i) == null){
                    columnLength.put(i, strings[i].length());
                }
                if (columnLength.get(i) < strings[i].length()){
                    columnLength.put(i, strings[i].length());
                }
            }
        }

        final StringBuilder formatString = new StringBuilder("");
        columnLength.entrySet().stream().forEach(e -> formatString.append("| %" + "-" + e.getValue() + "s "));
        formatString.append("|\n");

        StringBuilder borderLine = new StringBuilder("");
        columnLength.forEach((key, value) -> {
            borderLine.append("+");
            for (int i=0; i<value+2; i++){
              borderLine.append("-");
            }
            });
        borderLine.append("+");

        System.out.println(borderLine);
        Arrays.stream(sortedList2d).limit(1).forEach(item -> System.out.printf(formatString.toString(), item));
        System.out.println(borderLine);
        Arrays.stream(sortedList2d).forEach(item -> {
            if (item != headers){
                System.out.printf(formatString.toString(),item);
            }
        });
        System.out.println(borderLine);
    }

    private static boolean validateDate(String date) {
        String[] formatDate = date.split("-");

        int year = Integer.parseInt(formatDate[0]);
        int month = Integer.parseInt(formatDate[1]);
        int day = Integer.parseInt(formatDate[2]);

        if (month <= 12 && day <= (year % 4 == 0 ? daysInMonthForLeapYear[month-1] : daysInMonthForNotLeapYear[month-1])) {
            return true;
        }
        return false;
    }
}