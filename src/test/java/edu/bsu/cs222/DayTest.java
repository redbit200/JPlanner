package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DayTest {
    @Test
    public void testDayProperlyInstantiated(){
        LocalDate date = LocalDate.of(2000, 1, 1);
        Day testDay = new Day(date);
        int year = testDay.getYear();
        int monthOfYear = testDay.getMonthOfYear();
        int dayOfMonth = testDay.getDayOfMonth();
        Assert.assertEquals(2000, year);
        Assert.assertEquals(1, monthOfYear);
        Assert.assertEquals(1, dayOfMonth);
    }

    @Test
    public void testArrayLengthIsOneWithOneEvent(){
        Day testDay = new Day(LocalDate.of(2000, 1, 1));
        Event testEvent = new Event("test", "08:00", "09:45","General");
        testDay.addEvent(testEvent);
        Assert.assertEquals(1, testDay.getDailyEventList().size());
    }

    @Test
    public void testEventDataReturnsCorrect(){
        Day testDay = new Day(LocalDate.of(2000, 1, 1));
        Event testEvent = new Event("test", "08:00", "09:45","General");
        testDay.addEvent(testEvent);
        Event testEventInDay = testDay.getDailyEventList().get(0);
        Assert.assertEquals("test", testEventInDay.getEventTitle());
        Assert.assertEquals(8, testEventInDay.getStartHour());
        Assert.assertEquals(0, testEventInDay.getStartMinute());
        Assert.assertEquals(9, testEventInDay.getEndHour());
        Assert.assertEquals(45, testEventInDay.getEndMinute());
    }
}
