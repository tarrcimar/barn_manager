package example.controller;

import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userName;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginRegister;

    @FXML
    private Label feedbackLabel;

    JpaUserDAO userDAO = new JpaUserDAO();

    @FXML
    void initialize() {

        FadeController fadeController = new FadeController();

        loginButton.setOnAction(actionEvent -> { // when login button is clicked, get the values from the textboxes
            UserDAO uDaO = new JpaUserDAO();
            String username = userName.getText().trim();
            String password = userPassword.getText().trim();

            boolean found = false;

            List<User> users = uDaO.getUsers(); //if the username and password matches an existing user, switch to main window
            for (User user : users){
                if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                    System.out.println("Found user!");
                    found = true;
                }
            }

            if(!found) feedbackLabel.setVisible(true); //if user does not exist, provide an error message
            else{
                fadeController.fadeOut("/example/view/mainWindow.fxml", rootPane);
            }

        });

        loginRegister.setOnAction(actionEvent -> { //when register button is clicked, change window to register.fxml
            fadeController.fadeOut("/example/view/register.fxml", rootPane);
        });

    }
}
