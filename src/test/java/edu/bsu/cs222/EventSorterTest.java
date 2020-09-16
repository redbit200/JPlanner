package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class EventSorterTest {

    @Test
    public void Test0800EventIsFirst(){
        Day testDay = new Day(LocalDate.of(2019, 1, 1));
        Event Event1 = new Event("Event1", "10:00", "12:30","General");
        Event Event2 = new Event("Event2", "08:00", "09:30","General");
        Event Event3 = new Event("Event3", "10:45", "12:45","General");
        testDay.addEvent(Event1);
        testDay.addEvent(Event2);
        testDay.addEvent(Event3);
        EventSorter testSorter = new EventSorter();
        testSorter.sortChronologically(testDay);
        Assert.assertSame(Event2, testDay.getDailyEventList().get(0));
    }

    @Test
    public void TestSortByMinutesWhenEventsStartSameHour() {
        Day testDay = new Day(LocalDate.of(2019, 1, 1));
        Event Event1 = new Event("Event1", "10:45", "12:30","General");
        Event Event2 = new Event("Event2", "08:00", "09:30","General");
        Event Event3 = new Event("Event3", "10:00", "12:45","General");
        testDay.addEvent(Event1);
        testDay.addEvent(Event2);
        testDay.addEvent(Event3);
        EventSorter testSorter = new EventSorter();
        testSorter.sortChronologically(testDay);
        Assert.assertSame(Event3, testDay.getDailyEventList().get(1));
    }
}
