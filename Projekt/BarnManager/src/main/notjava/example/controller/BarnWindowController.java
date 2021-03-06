package example.controller;

import com.jfoenix.controls.JFXButton;
import example.Database.JpaBarnDAO;
import example.Database.JpaUserDAO;
import example.model.Barn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class BarnWindowController {

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
    private Hyperlink backToMainButton;

    @FXML
    private ListView<String> barnListView;

    @FXML
    private TextField barnTextName;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button submitBarn;

    @FXML
    private Button deleteBarn;

    @FXML
    private ImageView forageButton;

    @FXML
    private ImageView insightsButton;

    private ObservableList<Barn> barns;

    JpaBarnDAO all = new JpaBarnDAO();
    JpaUserDAO mall = new JpaUserDAO();




    @FXML
    void deleteBarn() {
        barns = FXCollections.observableArrayList();
        barns = FXCollections.observableArrayList(all.getBarnByUserId(userId));

        String chosen = barnListView.getSelectionModel().getSelectedItem();
        System.out.println(chosen);

        Barn todelete = new Barn();
        for (Barn b:barns) {
            if(b.getName().equals(chosen)){
                todelete = b;
                barnListView.getItems().remove(chosen);
            }
        }

        all.deleteBarn(todelete);
        reload();

    }

    @FXML
    void addNewBarn() {
        barns = FXCollections.observableArrayList();
        barns = FXCollections.observableArrayList(all.getBarnByUserId(userId));

        ObservableList<String> lista = FXCollections.observableArrayList();

        for (Barn barn : barns ) {
            lista.add(barn.getName());
        }


        if(lista.contains(barnTextName.getText())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Barn Addition Error");
            a.setHeaderText("Error");
            a.setContentText("Barn Already exists");
            a.show();
        }
        else {
            Barn barn = new Barn();
            barn.setName(barnTextName.getText());

            barn.setUser(mall.getUserById(userId));
            all.saveBarn(barn);

            barnListView.getItems().add(barn.getName());
        }

        reload();

    }

    public void selectedBarn(){

        String selected = barnListView.getSelectionModel().getSelectedItem();
        for (Barn barn: barns) {
            if(barn.getName() == selected)
            {
                ListController lc = new ListController();
                lc.setBarnId(barn.getId());
                lc.setBarnName(barn.getName());
            }
        }

        new FadeController().fadeOut("/example/view/animalList.fxml", rootPane);

    }

    public void reload(){
        barns = FXCollections.observableArrayList();
        barns = FXCollections.observableArrayList(all.getBarnByUserId(userId));

        ObservableList<String> lista = FXCollections.observableArrayList();

        for (Barn barn : barns ) {
            lista.add(barn.getName());
        }

        barnListView.setItems(lista);
    }

    @FXML
    void initialize() throws InterruptedException {

        //barnListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        barns = FXCollections.observableArrayList();
        barns = FXCollections.observableArrayList(all.getBarnByUserId(userId));

        BarnWindowController bwc = new BarnWindowController();
        bwc.setUserId(userId);
        bwc.setUsername(username);

        ForageWindowController fwc = new ForageWindowController();
        fwc.setUserId(userId);
        fwc.setUsername(username);


        usernameLabel.setText(getUsername());

        ObservableList<String> lista = FXCollections.observableArrayList();

        for (Barn barn : barns ) {
            lista.add(barn.getName());
        }

        barnListView.setItems(lista);

        backToMainButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/login.fxml", rootPane));
        submitBarn.setOnAction(actionEvent -> selectedBarn());
        forageButton.setOnMouseClicked(actionEvent -> new FadeController().fadeOut("/example/view/forageWindow.fxml", rootPane));
        insightsButton.setOnMouseClicked(actionEvent -> new FadeController().fadeOut("/example/view/chart.fxml", rootPane));
        deleteBarn.setOnAction(actionEvent -> deleteBarn());
    }
}
