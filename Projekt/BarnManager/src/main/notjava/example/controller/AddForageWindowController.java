package example.controller;

import example.Database.JpaDAO;
import example.Database.JpaForageDAO;
import example.model.Forage;
import example.model.ForageType;
import example.model.MeasurementUnit;
import example.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class AddForageWindowController {

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
    private Hyperlink backToForage;

    @FXML
    private Label usernameLabel;

    @FXML
    private ChoiceBox<String> forageNameChoice;

    @FXML
    private TextArea forageAmountTextArea;

    @FXML
    private ChoiceBox<String> unitTypeChoice;

    @FXML
    private Button addForageButton;

    private void addForageButtonClicked(){
        Forage newForage = new Forage();
        //a kiválasztott értéket eltároljuk
        String selectedForageName = forageNameChoice.getSelectionModel().getSelectedItem();
        String selectedUnitType = unitTypeChoice.getSelectionModel().getSelectedItem();
        //ez string viszont enumként tároljuk ezért át kell alakítani
        //nem a legjobb megoldás de legalább a miénk
        switch (selectedForageName){
            case "Hay":
                newForage.setName(ForageType.HAY);
                break;
            case "Worm":
                newForage.setName(ForageType.WORM);
                break;
            case "Meat":
                newForage.setName(ForageType.MEAT);
                break;
            case "Root":
                newForage.setName(ForageType.ROOT);
                break;
            case "Fungus":
                newForage.setName(ForageType.FUNGUS);
                break;
            case "Seed":
                newForage.setName(ForageType.SEED);
                break;
        }
        //ugyanez csak mérőegységgel
        switch (selectedUnitType){
            case "Kilogram":
                newForage.setUnit(MeasurementUnit.KG);
                break;
            case "Ton":
                newForage.setUnit(MeasurementUnit.TON);
                break;
            case "Box":
                newForage.setUnit(MeasurementUnit.BOX);
                break;
        }
        // string->int->long
        newForage.setAmount((long)Integer.parseInt(forageAmountTextArea.getText()));
        JpaDAO jpd = new JpaDAO();
        // vmiért userként tároltam ezért meg kell keresni azt a usert amelyiknél egyezik az id
        List<User> list = jpd.getUsers();
        for(User u : list){
            if(u.getId()==userId){
                newForage.setOwner_id(u);
            }
        }
        JpaForageDAO jpfd = new JpaForageDAO();
        //létrehozunk egy listát a már usernél létező takarmányokról
        List<Forage> existingForages = jpfd.getForagesByUserId(userId);
        boolean noError = true;
        //megnézzük hogy van-e már ilyen típusú, ha igen akkor errort dob fel
        for(Forage f : existingForages){
            if(f.getName()==newForage.getName()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Creation error");
                a.setHeaderText("Creation error");
                a.setContentText("You already have this type of forage.");
                a.show();
                noError = false;
            }
        }
        //ha error nélkül eljutott idáig akkor hozzáadja a databasehez és visszaküld a forages ablakba
        if(noError){
            jpfd.saveForage(newForage);
            ForageWindowController fw = new ForageWindowController();
            fw.setUserId(userId);
            fw.setUsername(username);
            new FadeController().fadeOut("/example/view/forageWindow.fxml", rootPane);
        }
    }

    @FXML
    void initialize(){
        usernameLabel.setText(getUsername());
        //választó menük feltöltése
        forageNameChoice.getItems().add("Hay");
        forageNameChoice.getItems().add("Worm");
        forageNameChoice.getItems().add("Meat");
        forageNameChoice.getItems().add("Root");
        forageNameChoice.getItems().add("Fungus");
        forageNameChoice.getItems().add("Seed");

        unitTypeChoice.getItems().add("Kilogram");
        unitTypeChoice.getItems().add("Ton");
        unitTypeChoice.getItems().add("Box");

        //gomb interakciók
        addForageButton.setOnAction(actionEvent -> addForageButtonClicked());
        backToForage.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/forageWindow.fxml", rootPane));
    }
}
