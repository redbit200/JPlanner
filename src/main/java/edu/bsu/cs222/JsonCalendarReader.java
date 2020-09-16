package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.HashMap;

public class JsonCalendarReader {
    private JsonArray DayJsonArray;
    private HashMap<LocalDate, Day> initialCalendarMap;

    public JsonCalendarReader(){
        this.DayJsonArray = null;
        this.initialCalendarMap = null;
    }


    public JsonArray getDayJsonArray(InputStream calendarData) {
        JsonParser jsonParser = new JsonParser();
        Reader reader = new InputStreamReader(calendarData);
        JsonElement rootElement = jsonParser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray days = rootObject.getAsJsonArray("days");
        DayJsonArray = days.getAsJsonArray();
        return DayJsonArray;
    }
    public HashMap<LocalDate, Day> convertSavedJsonCalendarToJava(){
        JsonArray savedDays = getDayJsonArray(getClass().getClassLoader().getResourceAsStream("CalendarSave.json"));
        HashMap<LocalDate, Day> importedJavaDays = new HashMap<>();
        for(int i=0; i<savedDays.size(); i++){
            String JsonDate = savedDays.get(i).getAsJsonObject().getAsJsonPrimitive("localDate").getAsString();
            Day JavaDay = new Day((JsonDateAsLocalDate(JsonDate)));
            if(savedDays.get(i).getAsJsonObject().getAsJsonPrimitive("note")!=null){
                JavaDay.setNoteText(savedDays.get(i).getAsJsonObject().getAsJsonPrimitive("note").getAsString());
            }
            if(savedDays.get(i).getAsJsonObject().getAsJsonArray("events").size()!=0){
                JsonArray JsonEventArray = savedDays.get(i).getAsJsonObject().getAsJsonArray("events");
                for(int j=0; j<JsonEventArray.size(); j++){
                    String JsonEventString = JsonEventArray.get(j).getAsJsonPrimitive().getAsString();
                    String[] eventArray = JsonEventString.split("~");
                    Event savedEvent = new Event(eventArray[0], eventArray[1], eventArray[2], "General");
                    JavaDay.addEvent(savedEvent);
                }
            }
            importedJavaDays.put(JsonDateAsLocalDate(JsonDate), JavaDay);
        }
        return importedJavaDays;
    }
    private LocalDate JsonDateAsLocalDate(String JsonDate){
        String[] tempStringArray = JsonDate.split("-");
        int[] tempIntegerArray = new int[3];
        for(int i=0; i<tempStringArray.length; i++){
            tempIntegerArray[i] = Integer.parseInt(tempStringArray[i]);
        }
        return LocalDate.of(tempIntegerArray[0],tempIntegerArray[1], tempIntegerArray[2]);
    }
}
