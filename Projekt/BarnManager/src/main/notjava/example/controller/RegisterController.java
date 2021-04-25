package example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import example.Database.JpaUserDAO;
import example.Database.UserDAO;
import example.model.GenderType;
import example.model.User;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController{
    public static final String USERNAME_PATTERN = //start with an alphanumeric character, followed by ._- or a-zA-Z0-9, at least
                                                 //at least 3, but maximum 18 characters long, close with alphanumeric
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        //Valami
    public static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);

    public static final String PASSWORD_PATTERN = //minimum 8 characters in length, has to contain at last one digit, one upper, one lowercase
                                                  //alphanumeric character
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);


    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField registerUserName;

    @FXML
    private JFXPasswordField registerPassword;

    @FXML
    private JFXPasswordField registerPassword2;

    @FXML
    private Hyperlink signInLink;

    @FXML
    private JFXButton registerRegisterButton;

    @FXML
    private Label passwordMatchLabel;

    final Tooltip tooltipPassword = new Tooltip();
    final Tooltip tooltipUser = new Tooltip();

    @FXML
    void initialize(){
        //when the link is clicked, go back to the login screen
        signInLink.setOnAction(actionEvent -> changeToLogin());

        //set up the tooltips for username and password fields
        tooltipUser.setText("3-18 characters long. Has to begin and end with alphanumeric.");
        registerUserName.setTooltip(tooltipUser);

        tooltipPassword.setText("8 characters long. 1 uppercase, 1 lowercase, 1 number.");
        registerPassword.setTooltip(tooltipPassword);
        registerPassword2.setTooltip(tooltipPassword);

        registerRegisterButton.setOnAction(actionEvent -> { // when button is clicked, create a new user
            boolean passwordMatch = false;
            boolean passwordCorrect = false;
            boolean usernameCorrect = false;
            String username = registerUserName.getText().trim();
            String password = registerPassword.getText().trim();
            String password2 = registerPassword2.getText().trim();

            //check if passwords match
            if(!password.equals(password2)){
                System.out.println(password.equals(password2));
                passwordMatchLabel.setVisible(true);
            } else {
                passwordMatch = true;
                passwordMatchLabel.setVisible(false);
            }

            //check if username is valid
            if(!isValidUsername(username)){
                registerUserName.setText("");
                registerUserName.setPromptText("Wrong Username Format");
            } else usernameCorrect = true;

            //check if passwords are valid according to the regex defined above
            if(!isValidPassword(password) || !isValidPassword(password2)){
                wrongFormatPassword(registerPassword);
                wrongFormatPassword(registerPassword2);

            } else passwordCorrect = true;

            //if everything as expected, create user
            if(usernameCorrect && passwordCorrect && passwordMatch) {
                System.out.println("Why doe");
                User newUser = new User(username, password);
                createUser(newUser);
            }

        });

    }

    //change the screen back to the login screen
    private void changeToLogin(){
        FadeController fadeController = new FadeController();
        fadeController.fadeOut("/example/view/login.fxml", rootPane);
    }

    //create the user by calling the DAO and writing it to the database
    private void createUser(User u){
        try {
            try(UserDAO uDao = new JpaUserDAO()){
                uDao.saveUser(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //change back to login.fxml
        changeToLogin();
    }

    public static boolean isValidUsername(final String username){
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(final String password){
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    //if the passwords does not satisfy the regex, clear the field
    public void wrongFormatPassword(PasswordField field){
        field.setText("");
        field.setPromptText("Wrong Password format!");
    }


}
