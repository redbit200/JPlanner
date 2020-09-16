package edu.bsu.cs222;

import java.time.LocalDate;
import java.util.HashMap;

 class CalendarWrapper {
    static HashMap<LocalDate, Day> dayMap = new HashMap<>();

    static boolean doesDayExist(LocalDate date){
        if(dayMap.containsKey(date)){
            return true;
        }
        return false;
    }

    static void addToDayMap(LocalDate date){
        Day newDay = new Day(date);
        dayMap.put(date, newDay);
    }

}
