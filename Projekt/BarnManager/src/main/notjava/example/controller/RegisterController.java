package example.controller;

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
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);


    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField registerUserName;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private PasswordField registerPassword2;

    @FXML
    private Hyperlink signInLink;

    @FXML
    private Button registerRegisterButton;

    @FXML
    private Label passwordMatchLabel;

    final Tooltip tooltipPassword = new Tooltip();
    final Tooltip tooltipUser = new Tooltip();

    @FXML
    void initialize(){
        signInLink.setOnAction(actionEvent -> changeToLogin());
        tooltipUser.setText("3-18 characters long. Has to begin and end with alphanumeric.");
        registerUserName.setTooltip(tooltipUser);

        registerRegisterButton.setOnAction(actionEvent -> { // when button is clicked, create a new user
            boolean passwordMatch = false;
            boolean passwordCorrect = false;
            boolean usernameCorrect = false;
            String username = registerUserName.getText().trim();
            String password = registerPassword.getText().trim();
            String password2 = registerPassword2.getText().trim();

            if(!password.equals(password2)){
                System.out.println(password.equals(password2));
                passwordMatchLabel.setVisible(true);
            } else {
                passwordMatch = true;
                passwordMatchLabel.setVisible(false);
            }
            if(!isValid(username)){

                registerUserName.setText("");
                registerUserName.setPromptText("Wrong Username Format");
            } else usernameCorrect = true;

            if(!isValidPassword(password) || !isValidPassword(password2)){
                tooltipPassword.setText("8 characters long. 1 uppercase, 1 lowercase, 1 number.");
                registerPassword.setTooltip(tooltipPassword);
                registerPassword2.setTooltip(tooltipPassword);

                wrongFormatText(registerPassword);
                wrongFormatText(registerPassword2);

            } else passwordCorrect = true;


            if(usernameCorrect && passwordCorrect && passwordMatch) {
                System.out.println("Why doe");
                User newUser = new User(username, password);
                createUser(newUser);
            }

        });

    }

    private void changeToLogin(){
        FadeController fadeController = new FadeController();
        fadeController.fadeOut("/example/view/login.fxml", rootPane);
    }

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

    public static boolean isValid(final String username){
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(final String password){
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    public static void wrongFormatText(PasswordField field){
        field.setText("");
        field.setPromptText("Wrong Password format!");
    }


}
