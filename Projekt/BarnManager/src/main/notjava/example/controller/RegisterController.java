package example.controller;

import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.GenderType;
import example.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController{

    @FXML
    private TextField registerUserName;

    @FXML
    private TextField registerPassword;

    @FXML
    private Button registerRegisterButton;


    @FXML
    private ComboBox<String> genderBox;

    @FXML
    void initialize(){
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

        registerRegisterButton.setOnAction(actionEvent -> { // when button is clicked, create a new user
            String username = registerUserName.getText().trim();
            String password = registerPassword.getText().trim();
            GenderType gender;

            if(genderBox.getValue().equals("Male")) gender = GenderType.MALE;
            else gender = GenderType.FEMALE;
            User newUser = new User(username, password, gender);

            try {
                try(UserDAO uDao = new JpaUserDAO();){
                    uDao.saveUser(newUser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //change back to login.fxml
            Stage stage;
            Parent root;

            stage = (Stage)registerRegisterButton.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("/example/view/login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
