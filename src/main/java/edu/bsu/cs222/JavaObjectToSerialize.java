package edu.bsu.cs222;

import java.util.ArrayList;

public class JavaObjectToSerialize {

    private String localDate;
    private String note;
    private ArrayList<String> events;

    public JavaObjectToSerialize(String localDate, String note, ArrayList<String> events){
        this.localDate = localDate;
        this.note = note;
        this.events = events;
    }
}
