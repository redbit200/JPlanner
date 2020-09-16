package edu.bsu.cs222;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {

    private String eventTitle;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String eventClassification;




    public Event(String title, String startTime, String endTime, String eventClassification){
        this.eventTitle = title;
        this.startHour = Integer.parseInt(splitTimestamps(startTime)[0]);
        this.startMinute = Integer.parseInt(splitTimestamps(startTime)[1]);
        this.endHour = Integer.parseInt(splitTimestamps(endTime)[0]);
        this.endMinute = Integer.parseInt(splitTimestamps(endTime)[1]);
        this.eventClassification = eventClassification;
    }
    public String[] splitTimestamps(String timestamp){
        return timestamp.split(":");
    }

    public String getEventTitle(){
        return eventTitle;
    }

    public int getStartHour(){
        return startHour;
    }

    public int getStartMinute(){
        return startMinute;
    }

    public int getEndHour(){
        return endHour;
    }

    public int getEndMinute(){
        return endMinute;
    }

    public String getEventClassification() {
        return eventClassification;
    }

    private String padTimestamp(int time){
        return String.format("%02d", time);
    }


    private static String convert24HourToAmPmFormat(String inputTime) {

        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(inputTime.substring(0, 2)));
        time.set(Calendar.MINUTE, Integer.parseInt(inputTime.substring(2, 4)));
        String minute = String.format("%02d", time.get(Calendar.MINUTE));
        String AM_PM = time.get(Calendar.AM_PM) == 0 ? "AM" : "PM";
        String finalTime = time.get(Calendar.HOUR) + ":" + minute;
        if(finalTime.equals("0:00")){
            finalTime = "12:00";
        }
        return finalTime + " " + AM_PM;
    }


    @Override
    public String toString() {
        String startTime = convert24HourToAmPmFormat(padTimestamp(startHour) + padTimestamp(startMinute));
        String endTime = convert24HourToAmPmFormat(padTimestamp(endHour) + padTimestamp(endMinute));
        return String.format("[%s]  %s\n%s - %s", eventClassification, eventTitle, startTime, endTime);

    }
}
