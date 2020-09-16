package edu.bsu.cs222;

import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;

public class JsonCalendarReaderTest {

    @Test
    public void testDaysArrayIsSize4(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("edu.bsu.cs222/testCalendarJSON");
        JsonCalendarReader jsonCalendarReader = new JsonCalendarReader();
        JsonArray dayArray = jsonCalendarReader.getDayJsonArray(inputStream);
        Assert.assertEquals(4, dayArray.size());
        System.out.println(dayArray);
    }
    @Test
    public void testEventArrayInFirstDayIsSize4(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("edu.bsu.cs222/testCalendarJSON");
        JsonCalendarReader jsonCalendarReader = new JsonCalendarReader();
        JsonArray dayArray = jsonCalendarReader.getDayJsonArray(inputStream);
        JsonArray EventJsonArray = dayArray.get(0).getAsJsonObject().getAsJsonArray("events");
        Assert.assertEquals(4, EventJsonArray.size());
        System.out.println(EventJsonArray);
    }
    @Test
    public void testDateFirstDayIs20191107(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("edu.bsu.cs222/testCalendarJSON");
        JsonCalendarReader jsonCalendarReader = new JsonCalendarReader();
        JsonArray dayArray = jsonCalendarReader.getDayJsonArray(inputStream);
        String testLocalDate = dayArray.get(0).getAsJsonObject().getAsJsonPrimitive("localDate").getAsString();
        Assert.assertEquals("2019-11-7", testLocalDate);
    }
}
