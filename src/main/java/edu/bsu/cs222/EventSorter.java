package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Collections;

class EventSorter {

    void sortChronologically(Day dayToSort) {
        ArrayList<Event> listToSort = dayToSort.getDailyEventList();
        for(int i=0; i<listToSort.size(); i++){
            for(int j=i+1; j<listToSort.size(); j++){
                if (listToSort.get(i).getStartHour() > listToSort.get(j).getStartHour()){
                   Event temporaryEvent = listToSort.get(i);
                   listToSort.set(i, listToSort.get(j));
                   listToSort.set(j, temporaryEvent);
                }
                else if(listToSort.get(i).getStartHour() == listToSort.get(j).getStartHour()){
                    if(listToSort.get(i).getStartMinute() > listToSort.get(j).getStartMinute()){
                        Event temporaryEvent = listToSort.get(i);
                        listToSort.set(i, listToSort.get(j));
                        listToSort.set(j, temporaryEvent);
                    }
                }
            }
        }
    }
}
