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
    private AnchorPane rootPane;

    @FXML
    private JFXButton backToMain;

    @FXML
    private Label usernameLabel;

    @FXML
    private JFXButton addAnimalButton;


    @FXML
    private JFXListView<Animal> barnList;

    private ObservableList<Animal> animals;

    @FXML
    void initialize(){
        usernameLabel.setText(getUsername());

        JpaAnimalDAO all = new JpaAnimalDAO();
        animals = FXCollections.observableArrayList(all.getAnimalsByBarnId(1));

        barnList.setItems(animals);
        barnList.setCellFactory(ListCellController -> new ListCellController());
        backToMain.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/mainWindow.fxml", rootPane));
        addAnimalButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/addToList.fxml", rootPane));
    }

}
