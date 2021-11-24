package com.oop.coursework;

import java.util.Collections;
import java.util.List;

public class Test {
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

    public static void main(String[] args) {
        // Retrieve Data from Files
        manager.retrieveDriverLocal(Formula1ChampionshipManager.driverFileName);
        manager.retrieveRaceLocal(Formula1ChampionshipManager.raceFileName);

        List<Race> raceListCopy = Formula1ChampionshipManager.raceList;
        Collections.sort(raceListCopy);

        raceListCopy.forEach(item -> System.out.println(item.getDate().getDay()+"/"+item.getDate().getMonth()+"/"+item.getDate().getYear()));
    }
}
