package example.controller;

import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.GenderType;
import example.model.User;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField registerUserName;

    @FXML
    private TextField registerPassword;

    @FXML
    private Button registerRegisterButton;

    @FXML
    private Button registerBackToLoginButton;



    @FXML
    private ComboBox<String> genderBox;

    @FXML
    void initialize(){
        FadeController fadeController = new FadeController();

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
            fadeController.fadeOut("/example/view/login.fxml", rootPane);
        });

    }


    public void backToLogin(ActionEvent actionEvent) throws IOException {

        FadeController fadeController = new FadeController();
        fadeController.fadeOut("/example/view/login.fxml", rootPane);

    }
}
