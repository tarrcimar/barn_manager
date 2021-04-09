package example.controller;

import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.GenderType;
import example.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController {

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

        loginButton.setOnAction(actionEvent -> { // when login button is clicked, get the values from the textboxes
            UserDAO uDaO = new JpaUserDAO();
            String username = userName.getText().trim();
            String password = userPassword.getText().trim();

            boolean found = false;

            List<User> users = uDaO.getUsers(); //if the username and password matches an existing user, switch to main window
            for (User user : users){
                if(user.getUserName().equals(username) && user.getUserPassword().equals(password)){
                    System.out.println("Found user!");
                    found = true;
                }
            }

            if(!found) feedbackLabel.setVisible(true); //if user does not exist, provide an error message
            else{ //change scene to mainWindow.fxml
                Stage stage;
                Parent root;

                stage = (Stage)loginButton.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("/example/view/mainWindow.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loginRegister.setOnAction(actionEvent -> { //when register button is clicked, change window to register.fxml
            Stage stage;
            Parent root;

            stage = (Stage)loginRegister.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("/example/view/register.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
