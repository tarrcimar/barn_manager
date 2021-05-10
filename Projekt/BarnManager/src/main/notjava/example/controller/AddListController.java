package example.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import example.Database.JpaAnimalDAO;
import example.Database.JpaBarnDAO;
import example.model.Animal;
import example.model.Barn;
import example.model.GenderType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddListController {
        public static long barnId;

        public long getBarnId() {
                return barnId;
        }

        public void setBarnId(long barnId) {
                this.barnId = barnId;
        }

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
        private TextField nameField;

        @FXML
        private ChoiceBox<String> genderChoice;

        @FXML
        private ChoiceBox<String> typeChoice;

        @FXML
        private TextField activityField;

        @FXML
        private TextField commentField;

        @FXML
        private Button addToListButton;

        private void addAnimalButtonClicked() {
                Animal newAnimal = new Animal();
                LocalDate now = LocalDate.now();
                String selectedGender = genderChoice.getSelectionModel().getSelectedItem();
                switch (selectedGender) {
                        case "Male":
                                newAnimal.setGender(GenderType.MALE);
                                break;
                        case "Female":
                                newAnimal.setGender(GenderType.FEMALE);
                                break;
                        }
                String selectType = typeChoice.getSelectionModel().getSelectedItem();
                switch (selectType) {
                        case "Horse":
                                newAnimal.setType("Horse");
                                break;
                        case "Cow":
                                newAnimal.setType("Cow");
                                break;
                        case "Goat":
                                newAnimal.setType("Goat");
                                break;
                        case "Pig":
                                newAnimal.setType("Pig");
                                break;
                }
                        newAnimal.setAddedOn(now);
                        String activity = activityField.getText();
                        newAnimal.setActivity(Integer.parseInt(activity));
                        String comment = commentField.getText();
                        newAnimal.setComment(comment);
                        JpaBarnDAO barnDAO = new JpaBarnDAO();
                        newAnimal.setBarn(barnDAO.getBarnByID(barnId));
                        JpaAnimalDAO animalDAO = new JpaAnimalDAO();
                        animalDAO.saveAnimal(newAnimal);
                        new FadeController().fadeOut("/example/view/animalList.fxml", rootPane);
                }
                @FXML
                void initialize()
                {
                        genderChoice.getItems().add("Male");
                        genderChoice.getItems().add("Female");

                        typeChoice.getItems().add("Horse");
                        typeChoice.getItems().add("Cow");
                        typeChoice.getItems().add("Goat");
                        typeChoice.getItems().add("Pig");

                        addToListButton.setOnAction(actionEvent -> addAnimalButtonClicked());
                        backToList.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/animalList.fxml", rootPane));

                }
        }

