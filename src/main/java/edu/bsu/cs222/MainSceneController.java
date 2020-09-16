package edu.bsu.cs222;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarPicker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import static javafx.application.Platform.exit;

public class MainSceneController extends UIController {

    @FXML CalendarPicker calendarPicker;

    @FXML ListView<String> eventsListView;

    @FXML TextArea notesArea;

    @FXML AnchorPane mainPane;

    @FXML Label displayDateLabel;

    @FXML ChoiceBox eventFilterChoiceBox;


    @FXML
    public void initialize(){
        initializeStyles();
        LocalDate currentDate = convertDateToLocalDate(Calendar.getInstance().getTime());
        populateMainScene(currentDate);
        displayDateLabel.setText(currentDate.toString());
        calendarPicker.calendarProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> ov, Calendar previousDate, Calendar newDate) {
                LocalDate dateSelected;
                if(newDate == null){
                    dateSelected = convertDateToLocalDate(previousDate.getTime());
                }else{
                    dateSelected = convertDateToLocalDate(newDate.getTime());
                }
                populateMainScene(dateSelected);
                displayDateLabel.setText(dateSelected.toString());
            }
        });
    }

    private LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @FXML
    private void populateMainScene(LocalDate dateSelected){
        if(CalendarWrapper.doesDayExist(dateSelected)) {
            Day dayObjectSelected = CalendarWrapper.dayMap.get(dateSelected);
            populateEventListView(dayObjectSelected, eventFilterChoiceBox.getValue().toString());
            notesArea.setText(dayObjectSelected.getNoteText());
        }else{
            eventsListView.getItems().clear();
            eventsListView.getItems().add("There are no events today!");
            notesArea.setText("There are no notes for today!");
        }
    }

    @FXML
    private void populateEventListView(Day day, String eventTypeFilter){
        eventsListView.getItems().clear();
        for(int i = 0; i < day.getDailyEventList().size(); i++){
            Event event = day.getDailyEventList().get(i);
            if(event.getEventClassification().equals(eventTypeFilter) || eventFilterChoiceBox.getValue().toString().equals("All")){
                String eventListViewString = event.toString();
                eventsListView.getItems().add(eventListViewString);
            }else{
                eventsListView.getItems().add("There are no events today in that classification.");
                break;
            }
        }
    }


    public void handleFilterChoiceBoxChange() {
        populateMainScene(convertDateToLocalDate(calendarPicker.getCalendar().getTime()));
    }
}

