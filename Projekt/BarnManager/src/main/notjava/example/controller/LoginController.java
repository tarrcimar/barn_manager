package example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import example.Database.BarnDAO;
import example.Database.JpaBarnDAO;
import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.Barn;
import example.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController {
    private long userId;


    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label feedbackLabel;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField userPassword;

    @FXML
    private Hyperlink loginRegister;

    @FXML
    private JFXButton loginButton;

    JpaUserDAO userDAO = new JpaUserDAO();

    @FXML
    void initialize() {
        BarnWindowController bwindow = new BarnWindowController();

        FadeController fadeController = new FadeController();

        loginButton.setOnAction(actionEvent -> { // when login button is clicked, get the values from the textboxes
            UserDAO uDaO = new JpaUserDAO();
            String username = userName.getText().trim();
            String password = userPassword.getText().trim();

            boolean found = false;

            List<User> users = uDaO.getUsers(); //if the username and password matches an existing user, switch to main window
            for (User user : users){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    userId = user.getId();
                    found = true;
                }
            }


            if(!found) feedbackLabel.setVisible(true); //if user does not exist, provide an error message
            else{
                bwindow.setUserId(userId);
                bwindow.setUsername(username);
                fadeController.fadeOut("/example/view/barnWindow.fxml", rootPane);
            }

        });

        loginRegister.setOnAction(actionEvent -> { //when register button is clicked, change window to register.fxml
            fadeController.fadeOut("/example/view/register.fxml", rootPane);
        });

    }
}
