package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static javafx.application.Platform.exit;

public class WriteNoteSceneController extends UIController {

    @FXML
    private TextArea noteTextArea;

    @FXML
    DatePicker noteDatePicker;

    @FXML
    public void initialize(){
        initializeStyles();
    }

    public void handleUpdateNoteButton(ActionEvent actionEvent) {
        LocalDate day = noteDatePicker.getValue();
        String noteText = noteTextArea.getText();
        if(CalendarWrapper.doesDayExist(day)){
            Day dayObject = CalendarWrapper.dayMap.get(day);
            dayObject.setNoteText(noteText);
        }else{
            CalendarWrapper.addToDayMap(day);
            Day dayObject = CalendarWrapper.dayMap.get(day);
            dayObject.setNoteText(noteText);
        }
        try {
            Parent createEventScene = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainScene.fxml")));
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(new Scene(createEventScene));
        }catch(IOException e){
            handleErrorDialog("System failed to load UI - Main Scene");
            exit();
        }


    }


}
