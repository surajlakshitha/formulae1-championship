package com.oop.coursework;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GUI {

    static String[] headersInDrivers = new String[]{"Id", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "# of Points", "# of Races"};
    static String[] headersInRace = new String[]{"Date", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

    public GUI() {
        // Main Frame Properties
        JFrame jFrame = new JFrame();

        // Header Fields
        JLabel jLabel = new JLabel("-Formula 1 Championship League-", SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial", Font.BOLD, 20));
        jLabel.setBounds(100, 20, 600, 22);

        // Search Fields
        JPanel jPanelSearch = new JPanel();
        jPanelSearch.setBounds(250, 80, 300, 50);
        jPanelSearch.setBackground(Color.lightGray);
        jPanelSearch.setLayout(null);

        JTextField searchText = new JTextField();
        searchText.setBounds(0, 0, 150, 30);

        JButton search = new JButton("Search");
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.setBounds(170, 0, 100, 30);

        jPanelSearch.add(searchText);
        jPanelSearch.add(search);

        // Table Configurations
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setLayout(new ScrollPaneLayout());
        jScrollPane.setBackground(Color.lightGray);
        jScrollPane.setBounds(15, 230, 800, 400);

        // Table Data
        String[][] rowData = convertDriverDataTo2DArray(Formula1ChampionshipManager.formula1DriverList, false, false);
        String[] colData = headersInDrivers;
        DefaultTableModel model = new DefaultTableModel(rowData, colData);

        JTable jTable = new JTable(model);
        jTable.setRowHeight(25);
        jTable.setCellSelectionEnabled(true);
        jTable.setFont(new Font("Arial", Font.ITALIC, 14));
        jTable.setAutoCreateRowSorter(true); // Enable in-build sorter

        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        jScrollPane.setViewportView(jTable);

        // Buttons Fields
        JPanel jPanelButtons = new JPanel();
        jPanelButtons.setBounds(50, 150, 800, 30);
        jPanelButtons.setBackground(Color.lightGray);
        jPanelButtons.setLayout(null);

        JButton sortByPoints = new JButton("Sort By Points");
        sortByPoints.setBackground(Color.BLACK);
        sortByPoints.setForeground(Color.WHITE);
        sortByPoints.setBounds(0, 0, 120, 30);

        JButton sortByWins = new JButton("Sort By Wins");
        sortByWins.setBackground(Color.BLACK);
        sortByWins.setForeground(Color.WHITE);
        sortByWins.setBounds(150, 0, 120, 30);

        JButton displayRace = new JButton("Display Races");
        displayRace.setBackground(Color.BLACK);
        displayRace.setForeground(Color.WHITE);
        displayRace.setBounds(300, 0, 120, 30);

        JButton generate = new JButton("Generate Race");
        generate.setBackground(Color.BLACK);
        generate.setForeground(Color.WHITE);
        generate.setBounds(450, 0, 120, 30);

        JButton reset = new JButton("Reset Races");
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.setBounds(600, 0, 120, 30);


        jPanelButtons.add(sortByPoints);
        jPanelButtons.add(sortByWins);
        jPanelButtons.add(displayRace);
        jPanelButtons.add(generate);
        jPanelButtons.add(reset);

        // Add ActionListeners to the Button
        // TODO Invalid Message
        search.addActionListener(e -> {
            List<Race> filteredList = manager.filterByDriverId(searchText.getText());
            String[][] sortByPointData = convertRaceDataTo2DArray(filteredList);
            model.setDataVector(sortByPointData, colData);
        });

        sortByPoints.addActionListener(e -> {
            String[][] sortByPointData = convertDriverDataTo2DArray(Formula1ChampionshipManager.formula1DriverList, true, false);
            model.setDataVector(sortByPointData, colData);
        });

        sortByWins.addActionListener(e -> {
            String[][] sortByPointData = convertDriverDataTo2DArray(Formula1ChampionshipManager.formula1DriverList, false, true);
            model.setDataVector(sortByPointData, colData);
        });

        displayRace.addActionListener(e -> {
            String[][] sortByPointData = convertRaceDataTo2DArray(Formula1ChampionshipManager.raceList);
            String[] colHeader = headersInRace;
            model.setDataVector(sortByPointData, colHeader);
        });

        generate.addActionListener(e -> {
            manager.generateRandomRace(); // generate random race
            String[][] sortByPointData = convertRaceDataTo2DArray(Formula1ChampionshipManager.raceList);
            String[] colHeader = headersInRace;
            model.setDataVector(sortByPointData, colHeader);
        });

        reset.addActionListener(e -> {
            String[][] sortByPointData = convertDriverDataTo2DArray(Formula1ChampionshipManager.formula1DriverList, false, false);
            model.setDataVector(sortByPointData, colData);
        });

        // Add Child Components to Parent Components
        jFrame.add(jLabel);
        jFrame.add(jPanelSearch);
        jFrame.add(jPanelButtons);
        jFrame.add(jScrollPane);

        // Properties
        jFrame.setTitle("Formula1 Championship - GUI");
        jFrame.setAlwaysOnTop(true);
        jFrame.setSize(850, 700);
        jFrame.getContentPane().setBackground(Color.lightGray);
        jFrame.setLayout(null); // To Use SetBound Methods
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        ImageIcon icon = new ImageIcon("icon.png");
        jFrame.setIconImage(icon.getImage());

    }


    public static void main(String[] args) {
        // Retrieve Data from Files
        manager.retrieveDriverLocal(Formula1ChampionshipManager.driverFileName);
        manager.retrieveRaceLocal(Formula1ChampionshipManager.raceFileName);

        new GUI();
    }

    // Only applicable for Formulary1Driver List
    private String[][] convertDriverDataTo2DArray(List<Formula1Driver> source, boolean isSortedByPoints, boolean isSortedByWins) {
        List<Formula1Driver> data = source;

        if (isSortedByPoints) {
            Collections.sort(data, new PointComparator());
        }

        if (isSortedByWins) {
            Collections.sort(data, new WinsComparator());
        }

        String[][] formattedArray = new String[data.size()][9];

        for (int i = 0; i < data.size(); i++) {
            Formula1Driver formula1Driver = data.get(i);
            formattedArray[i] = new String[]{
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
        return formattedArray;
    }

    // Only applicable for Race List
    private String[][] convertRaceDataTo2DArray(List<Race> source) {
        List<Race> dataOfTheRace = source;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Collections.sort(dataOfTheRace, (o1, o2) -> {
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = simpleDateFormat.parse(o1.getDateString());
                date2 = simpleDateFormat.parse(o2.getDateString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date1.compareTo(date2);
        });

        String[][] formattedArray = new String[dataOfTheRace.size()][11];
        for (int i = 0; i < dataOfTheRace.size(); i++) {
            Race race = dataOfTheRace.get(i);

            String[] temp = new String[10];
            temp[0] = race.getDateString();
            race.getDriverPlaceMap().forEach((key, value) -> {
                temp[value] = key.getName();
            });
            formattedArray[i] = temp;
        }
        return formattedArray;
    }
}