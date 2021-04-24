package example.controller;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import example.Database.DAO;
import example.Database.JpaForageDAO;
import example.model.Forage;
import example.model.ForageType;
import example.model.MeasurementUnit;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.util.List;

public class ForageWindowController {

    public static String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Hyperlink backToMain;

    @FXML
    private Label usernameLabel;

    @FXML
    private JFXListView<Forage> forageListView;

    private ObservableList<Forage> forages;

    @FXML
    void initialize(){
        forages = FXCollections.observableArrayList();
        usernameLabel.setText(getUsername());
        backToMain.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/mainWindow.fxml", rootPane));

        JpaForageDAO all = new JpaForageDAO();
        forages = FXCollections.observableArrayList(all.getForagesByUserId(userId));

        /*Forage myForage = new Forage();
        myForage.setName(ForageType.HAY);
        myForage.setAmount((long)200);
        myForage.setUnit(MeasurementUnit.KG);

        Forage myForage2 = new Forage();
        myForage2.setName(ForageType.WORM);
        myForage2.setAmount((long)50);
        myForage2.setUnit(MeasurementUnit.BOX);

        forages.addAll(myForage,myForage2);*/

        forageListView.setItems(forages);
        forageListView.setCellFactory(ForageCellController -> new ForageCellController());
    }


}

