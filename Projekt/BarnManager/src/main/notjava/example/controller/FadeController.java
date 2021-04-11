package example.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FadeController {

    public void fadeOut(String target, AnchorPane rootPane){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadNextScene(target, rootPane);
            }
        });
        fadeTransition.play();
    }

    public void loadNextScene(String target, AnchorPane rootPane){
        Parent secondView;
        try {
            secondView = (AnchorPane) FXMLLoader.load(getClass().getResource(target));

            Scene newScene = new Scene(secondView);

            Stage curStage = (Stage) rootPane.getScene().getWindow();

            curStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
