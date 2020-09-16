package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class EventTest {

    @Test
    public void TestStringTimeInputOutputsAsInt(){
        Event testEvent = new Event("TestEvent", "00:00", "00:00", "General");
        int testStartHour = testEvent.getStartHour();
        Assert.assertEquals(0, testStartHour);
    }

    @Test
    public void TestStartHourIs8andStartMinuteIs15(){
        Event testEvent = new Event("TestEvent", "08:15", "09:30","General");
        int testStartHour = testEvent.getStartHour();
        int testStartMinute = testEvent.getStartMinute();
        Assert.assertEquals(8, testStartHour);
        Assert.assertEquals(15, testStartMinute);
    }

    @Test
    public void TestEndHourIs9andEndMinuteIs30(){
        Event testEvent = new Event("TestEvent", "08:15", "09:30","General");
        int testEndHour = testEvent.getEndHour();
        int testEndMinute = testEvent.getEndMinute();
        Assert.assertEquals(9, testEndHour);
        Assert.assertEquals(30, testEndMinute);
    }
}
