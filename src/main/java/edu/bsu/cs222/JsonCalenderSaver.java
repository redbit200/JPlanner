package edu.bsu.cs222;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

class JsonCalenderSaver {



    void saveJsonCalendarToFile(){
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("CalendarSave.json");
            writer.write(gson.toJson(formatJavaCalendarData(CalendarWrapper.dayMap)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private ArrayList<JavaObjectToSerialize> formatJavaCalendarData(Map<LocalDate, Day> dataToBeSaved){
        ArrayList<JavaObjectToSerialize> JavaObjectsToSerialize = new ArrayList<>();
        for (Map.Entry<LocalDate,Day> entry : dataToBeSaved.entrySet()) {
            String localDate = entry.getKey().getYear() +"-"+ entry.getKey().getMonth() +"-"+ entry.getKey().getDayOfMonth();
            String note = entry.getValue().getNoteText();
            ArrayList<String> events = new ArrayList<String>();
            for(int i=0; i<entry.getValue().getDailyEventList().size(); i++){
                Event eventObject = entry.getValue().getDailyEventList().get(i);
                String eventToBeSaved = eventObject.getEventTitle() + "~" + eventObject.getStartHour() + ":" + eventObject.getStartMinute() + "~" + eventObject.getEndHour() + ":" + eventObject.getEndMinute();
                events.add(eventToBeSaved);
            }
           JavaObjectToSerialize nextObject = new JavaObjectToSerialize(localDate, note, events);
            JavaObjectsToSerialize.add(nextObject);
        }
        return JavaObjectsToSerialize;
    }
}
