package example.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import example.Database.JpaAnimalDAO;
import example.Database.JpaBarnDAO;
import example.model.Animal;
import example.model.Barn;
import example.model.GenderType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        private Hyperlink backToList;

        @FXML
        private ChoiceBox<String> genderChoice;

        @FXML
        private ChoiceBox<String> typeChoice;

        @FXML
        private JFXTextField activityField;

        @FXML
        private JFXTextField commentField;

        @FXML
        private Button addToListButton;

        @FXML
        private JFXTextField countField;



        private void addAnimalButtonClicked() {
                int counter;
                if(countField.getText().trim().isEmpty()){
                        counter = 1;
                }
                else{
                        counter = Integer.parseInt(countField.getText());
                }
                for (int i =0; i < counter; i++) {
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
                }
                new FadeController().fadeOut("/example/view/animalList.fxml", rootPane);

        }
        @FXML
        void initialize()
        {

                countField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue,
                                            String newValue) {
                                if (!newValue.matches("\\d*")) {
                                        countField.setText(newValue.replaceAll("[^\\d]", ""));
                                }
                        }
                });

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

