package com.oop.coursework;

import javax.swing.*;

public class GUI {

    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

    public static void main(String[] args) {

        // Update List from File
        manager.retrieveDriverLocal(Formula1ChampionshipManager.driverFileName);
        manager.retrieveRaceLocal(Formula1ChampionshipManager.raceFileName);

        // Main Frame
        JFrame parent = new JFrame();

        // Child Pane
        JPanel scrollPane = new JPanel();
        scrollPane.setBounds(30,60,800,700);

        // Table
        JTable table = new JTable(getAllDrivers(), new String[]{"Id", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "# of Points", "# of Races"});
        table.setRowHeight(20);
        table.setBounds(50,50,600,500);

        // Add Child Components to Parent
        scrollPane.add(table);
        parent.add(scrollPane);

        // Frame Properties
        parent.setVisible(true);
        parent.setSize(850,750);

    }

    private static String[][] getAllDrivers() {
        String[][] sortedList2d = new String[Formula1ChampionshipManager.formula1DriverList.size()+1][9];
        for (int i = 0; i < Formula1ChampionshipManager.formula1DriverList.size(); i++) {
            Formula1Driver formula1Driver = Formula1ChampionshipManager.formula1DriverList.get(i);
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
        return sortedList2d;
    }
}