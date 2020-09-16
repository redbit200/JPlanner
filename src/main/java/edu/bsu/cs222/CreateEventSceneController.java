package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static javafx.application.Platform.exit;

public class CreateEventSceneController extends UIController{
    private CalendarWrapper calendar = new CalendarWrapper();


    @FXML
    private DatePicker eventDateSelected;

    @FXML
    private TextField eventTitleName, eStartTime, eEndTime;

    @FXML
    private ChoiceBox eventTypeChoice;


    @FXML
    public void initialize(){
        initializeStyles();
        eventDateSelected.setValue(LocalDate.now());

    }

    public void handleCreateEventButton() {
        LocalDate day = eventDateSelected.getValue();
        createNewEvent(day);
        handleMainSceneMenuButton();
    }

    private void createNewEvent(LocalDate eventDay){
            EventSorter sorter = new EventSorter();
            String eventTitle = eventTitleName.getText();
            String eventStartTime = eStartTime.getText();
            String eventEndTime = eEndTime.getText();
            Event newEvent = new Event(eventTitle, eventStartTime, eventEndTime, eventTypeChoice.getValue().toString());
            if (CalendarWrapper.doesDayExist(eventDay)) {
                Day dayObject = CalendarWrapper.dayMap.get(eventDay);
                dayObject.addEvent(newEvent);
                sorter.sortChronologically(dayObject);
                CalendarWrapper.dayMap.put(eventDay, dayObject);
            } else {
                CalendarWrapper.addToDayMap(eventDay);
                Day dayObject = CalendarWrapper.dayMap.get(eventDay);
                dayObject.addEvent(newEvent);
            }

    }





}
