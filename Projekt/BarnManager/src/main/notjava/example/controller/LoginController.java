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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private JFXButton loginRegister;

    @FXML
    private JFXButton loginButton;

    JpaUserDAO userDAO = new JpaUserDAO();

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        MainWindowController window = new MainWindowController();

        FadeController fadeController = new FadeController();

        loginButton.setOnAction(actionEvent -> { // when login button is clicked, get the values from the textboxes
            UserDAO uDaO = new JpaUserDAO();
            BarnDAO bDao = new JpaBarnDAO();
            String username = userName.getText().trim();
            String password = userPassword.getText().trim();

            boolean found = false;

            List<User> users = uDaO.getUsers(); //if the username and password matches an existing user, switch to main window
            for (User user : users){
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    System.out.println("Found user!");
                    userId = user.getId();
                    found = true;
                }
            }

            //példa az istállók lekérésére

            List<Barn> barns = bDao.getBarns();
            for(Barn b : barns){
                System.out.println(b.getName());
            }



            if(!found) feedbackLabel.setVisible(true); //if user does not exist, provide an error message
            else{
                System.out.println("In login: " + userId);
                window.setUserId(userId);
                window.setUsername(username);
                fadeController.fadeOut("/example/view/mainWindow.fxml", rootPane);
            }

        });

        loginRegister.setOnAction(actionEvent -> { //when register button is clicked, change window to register.fxml
            fadeController.fadeOut("/example/view/register.fxml", rootPane);
        });

    }
}
