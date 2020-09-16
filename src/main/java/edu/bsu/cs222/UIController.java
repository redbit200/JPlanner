package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import static javafx.application.Platform.exit;

public class UIController {

    private static final Rectangle2D SCREEN_BOUNDS= Screen.getPrimary().getVisualBounds();
    private static double[] offset_XY;


    @FXML
    AnchorPane mainPane;

    @FXML
    private void handleCloseButton(){
        exit();
    }

    @FXML
    private void handleMinimizeButton(){
        ((Stage) (mainPane).getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void handleCreateEventMenuButton() {
        try {
            Parent createEventScene = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("CreateEventScene.fxml")));
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(new Scene(createEventScene));
            enableDrag(createEventScene, stage);
        }catch(IOException e){
            handleErrorDialog("System failed to load UI - Event Scene");
            exit();
        }
    }

    @FXML
    private void handleWriteNoteMenuButton() {
        try {
            Parent writeNoteScene = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("WriteNoteScene.fxml")));
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(new Scene(writeNoteScene));
            enableDrag(writeNoteScene, stage);
        } catch (IOException e) {
            handleErrorDialog("System failed to load UI - Note Scene");

            exit();
        }
    }

    @FXML
     void handleMainSceneMenuButton() {
        try {
            Parent mainScene = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainScene.fxml")));
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(new Scene(mainScene));
            enableDrag(mainScene, stage);
        }catch(IOException e){
            handleErrorDialog("System failed to load UI - Main Scene");
            exit();
        }
    }

    @FXML
    void initializeStyles(){
        mainPane.getStylesheets().add("styles.css");
    }

    void handleErrorDialog(String errorMessage){
        Alert missingInputsError = new Alert(Alert.AlertType.ERROR);
        missingInputsError.setHeaderText("Error!");
        missingInputsError.setContentText(errorMessage);
        missingInputsError.show();
    }

    static void enableDrag(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent p) -> {
            offset_XY= new double[]{p.getSceneX(), p.getSceneY()};
        });

        root.setOnMouseDragged((MouseEvent d) -> {
            if (d.getScreenY()<(SCREEN_BOUNDS.getMaxY()-20))
                stage.setY(d.getScreenY() - offset_XY[1]);
            stage.setX(d.getScreenX() - offset_XY[0]);
        });

        root.setOnMouseReleased((MouseEvent r)-> {
            if (stage.getY()<0.0) stage.setY(0.0);
        });
    }


}
