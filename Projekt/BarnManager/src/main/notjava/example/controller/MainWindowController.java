package example.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController {
    public static long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private URL location;

    @FXML
    private Label usernameLabel;

    @FXML
    private Hyperlink logOutLink;

    @FXML
    void initialize() {
        System.out.println("In mainwindow: " + userId);
        logOutLink.setOnAction(actionEvent -> changeToLogin());
        usernameLabel.setText(getUserName());

    }

    //change the screen back to the login screen
    private void changeToLogin(){
        FadeController fadeController = new FadeController();
        fadeController.fadeOut("/example/view/login.fxml", rootPane);
    }
}
