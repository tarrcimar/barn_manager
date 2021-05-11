package example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import example.Database.JpaAnimalDAO;
import example.Database.JpaForageDAO;
import example.model.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
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


    public static long barnId;

    public long getBarnId() {
        return barnId;
    }

    public void setBarnId(long barnId) {
        this.barnId = barnId;
    }


    public static String barnName;

    public String getBarnName() {
        return barnName;
    }

    public void setBarnName(String barnName) {
        this.barnName = barnName;
    }



    @FXML
    private AnchorPane rootPane;

    @FXML
    private Hyperlink backToMain;

    @FXML
    private Label usernameLabel;

    @FXML
    private JFXButton addAnimalButton;


    @FXML
    private JFXListView<Animal> barnList;

    private ObservableList<Animal> animals;


    @FXML
    void initialize(){
        usernameLabel.setText(getBarnName());
        AddListController alc = new AddListController();
        alc.setBarnId(barnId);
        JpaAnimalDAO all = new JpaAnimalDAO();
        animals = FXCollections.observableArrayList(all.getAnimalsByBarnId(barnId));

        barnList.setItems(animals);
        barnList.setCellFactory(ListCellController -> new ListCellController());
        backToMain.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/barnWindow.fxml", rootPane));
        addAnimalButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/addToList.fxml", rootPane));
    }

}
