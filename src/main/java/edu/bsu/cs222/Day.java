package edu.bsu.cs222;

import java.time.LocalDate;
import java.util.ArrayList;

public class Day {
    private int dayOfMonth;
    private int monthOfYear;
    private int year;
    private ArrayList<Event> dailyEventList;
    private String noteText;

    public Day(LocalDate date) {
        this.year = date.getYear();
        this.monthOfYear = date.getMonthValue();
        this.dayOfMonth = date.getDayOfMonth();
        this.dailyEventList = new ArrayList<Event>();
        this.noteText = "There are no notes for today!";

    }

    void addEvent(Event newEvent) {
        dailyEventList.add(newEvent);
    }


    int getYear() {
        return year;
    }


    int getMonthOfYear() {
        return monthOfYear;
    }


    int getDayOfMonth() {
        return dayOfMonth;
    }

    ArrayList<Event> getDailyEventList(){
        return dailyEventList;
    }

    void setNoteText(String text){
        this.noteText = text;
    }

    String getNoteText(){
        return this.noteText;
    }
}
