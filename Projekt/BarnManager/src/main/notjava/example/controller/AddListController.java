package example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import example.model.GenderType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddListController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane rootPane;

        @FXML
        private JFXButton backToList;

        @FXML
        private Label usernameLabel;

        @FXML
        private ChoiceBox<GenderType> genderChoice;

        @FXML
        private ChoiceBox<?> animalTypeChoice;

        @FXML
        private TextField nameField;

        @FXML
        private TextField activityField;

        @FXML
        private TextField commentField;

        @FXML
        private Button addToListButton;

        @FXML
        void initialize() {

                 backToList.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/list.fxml", rootPane));
        }
}
