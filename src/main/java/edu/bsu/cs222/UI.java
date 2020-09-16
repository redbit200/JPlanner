package edu.bsu.cs222;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;


public class UI extends Application{

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("taskbar_icon.png"));
        Parent mainScene = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainScene.fxml")));
        primaryStage.setScene(new Scene(mainScene));
        UIController.enableDrag(mainScene, primaryStage);
        JsonCalendarReader reader = new JsonCalendarReader();
        CalendarWrapper.dayMap = reader.convertSavedJsonCalendarToJava();
        primaryStage.show();


    }


}