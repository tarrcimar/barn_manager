package example.controller;

import com.jfoenix.controls.JFXButton;
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
    private Hyperlink logOutLink;

    @FXML
    private Label usernameLabel;

    @FXML
    private JFXButton forageButton;

    @FXML
    private JFXButton insightsButton;

    @FXML
    private JFXButton listaElem;

    @FXML
    void initialize() throws InterruptedException {
        ForageWindowController fw = new ForageWindowController();
        fw.setUserId(userId);
        fw.setUsername(username);

        ListController ls = new ListController();
        ls.setUserId(userId);

        logOutLink.setOnAction(actionEvent -> changeToLogin());
        usernameLabel.setText(getUsername());

        //ugrás a chartra
        insightsButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/chart.fxml", rootPane));

        //ugrás takarmányablakra
        forageButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/forageWindow.fxml", rootPane));

        //ugrás a listára
        listaElem.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/animalList.fxml",rootPane));

        /* példák a lekérdezésekre

        DAO all = new JpaDAO();
        System.out.println("In main window: " + userId);
        List<Barn> barnList = all.getBarnsByUserId(userId);
        foreach (Barn b : barnList){
            System.out.println(b.getName());
        }

        List<Animal> animalList = all.getAnimalsByBarnId(1);
        System.out.println(animalList.size());
        for (Animal a: animalList) {
            System.out.println(a.getType() + " " + a.getId());
        }
         */
    }
    //change the screen back to the login screen
    private void changeToLogin(){
        FadeController fadeController = new FadeController();
        fadeController.fadeOut("/example/view/login.fxml", rootPane);
    }
}
