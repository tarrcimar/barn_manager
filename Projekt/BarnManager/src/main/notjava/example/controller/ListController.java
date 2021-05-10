package example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import example.Database.JpaAnimalDAO;
import example.Database.JpaForageDAO;
import example.model.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController {
    public static long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton addAnimalButton;

    @FXML
    private JFXButton backToMain;

    @FXML
    void initialize(){
    }

}
