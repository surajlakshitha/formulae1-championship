package com.oop.coursework;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Formula1ChampionshipManager implements ChampionshipManager {

    static List<Formula1Driver> formula1DriverList = new ArrayList<>();
    static List<Race> raceList = new ArrayList<>();
    static int[] points = {25,18,15,12,10,8,6,4,2,1};
    static String driverFileName = "DriverDetails.txt";
    static String raceFileName = "RaceDetails.txt";

    @Override
    public void createNewFormula1Driver(Driver formula1Driver) {
        System.out.println("createNewFormula1Driver");
        formula1DriverList.add((Formula1Driver) formula1Driver);
        saveDriverLocal(driverFileName);
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
        saveDriverLocal(driverFileName);
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
        saveDriverLocal(driverFileName);
    }

    @Override
    public Formula1Driver getFormula1DriverStatistics(int driverId) {
        System.out.println("getFormula1DriverStatistics");

        // Find Object from the List
        Formula1Driver checkForDriver = formula1DriverList.stream().filter(driver -> driver.getDriverId() == (driverId)).collect(Collectors.toList()).stream().findFirst().orElse(null);
        if (checkForDriver == null) {
            System.out.println("Invalid Driver Id");
        }
        return checkForDriver;
    }

    @Override
    public void updateRaceStats(Map<Integer, Integer> raceResult, Date date) {
        System.out.println("updateRaceStats");
        Map<Formula1Driver, Integer> tempMap = new HashMap<>();
        raceResult.forEach((key, value) -> {
            Formula1Driver tempDriver = formula1DriverList.stream().filter(item -> item.getDriverId() == key).collect(Collectors.toList()).stream().findFirst().orElse(null);
            formula1DriverList.set(formula1DriverList.indexOf(tempDriver), updatePointAndPlace(tempDriver, value));
            tempMap.put(tempDriver, value);
        });
        raceList.add(new Race(date, tempMap));

        saveDriverLocal(driverFileName);
        saveRaceLocal(raceFileName);

    }

    @Override
    public void saveDriverLocal(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, false);
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
    public void retrieveDriverLocal(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
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
    public void saveRaceLocal(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Race race : raceList) {
                oos.writeObject(race);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveRaceLocal(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (;;) {
                try {
                    Race race = (Race) ois.readObject();
                    raceList.add(race);
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

    @Override
    public List<Formula1Driver> sortDriversByPoint() {
        Collections.sort(formula1DriverList, new PointComparator());
        return formula1DriverList;
    }

    @Override
    public List<Formula1Driver> sortDiversByNumberOfFirstPlaces() {
        Collections.sort(formula1DriverList, new WinsComparator());
        return formula1DriverList;
    }

    @Override
    public List<Race> filterByDriverId(String driverId) {
        List<Race> filterRace = new ArrayList<>();
        for (Race race: raceList) {
            race.getDriverPlaceMap().forEach((key, value) -> {
                if(key.getDriverId() == Integer.parseInt(driverId)){
                    filterRace.add(race);
                }
            });
        }
        return filterRace;

    }

    private Formula1Driver updatePointAndPlace(Formula1Driver tempDriver, Integer value) {
        if (value == 1) tempDriver.setNumberOfFirstPlaces(tempDriver.getNumberOfFirstPlaces()+1);
        if (value == 2) tempDriver.setNumberOfSecondPlaces(tempDriver.getNumberOfSecondPlaces()+1);
        if (value == 3) tempDriver.setNumberOfThirdPlaces(tempDriver.getNumberOfThirdPlaces()+1);

        tempDriver.setNumberOfPoints(tempDriver.getNumberOfPoints()+points[value-1]);
        return tempDriver;
    }
}

class WinsComparator implements Comparator<Formula1Driver>{
    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {
        return o2.getNumberOfFirstPlaces() - o1.getNumberOfFirstPlaces();
    }
}

class PointComparator implements Comparator<Formula1Driver>{
    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {
        return o2.getNumberOfPoints() - o1.getNumberOfPoints();
    }
}