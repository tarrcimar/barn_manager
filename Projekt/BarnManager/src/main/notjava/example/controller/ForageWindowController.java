package example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

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
    private ListView<String> forageListView;

    ObservableList<String> list = FXCollections.observableArrayList(
            "alma","banan","ananasz","kukorica"
    );

    @FXML
    void initialize(){
        usernameLabel.setText(getUsername());
        backToMain.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/mainWindow.fxml", rootPane));
        forageListView.setItems(list);
    }
}
