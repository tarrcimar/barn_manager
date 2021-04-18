package example.controller;


import example.Database.DAO;
import example.Database.JpaDAO;
import example.model.Animal;
import example.model.Barn;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController {
    public static long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() throws InterruptedException {

        /* Példák a lekérdezésekre
        DAO minden = new JpaDAO();
        System.out.println("In mainwindow: " + userId);
        Barn b = minden.getBarnByUserId(userId);
        System.out.println(b.getName());

        List<Animal> animalList = minden.getAnimalsByBarnId(1);
        System.out.println(animalList.size());
        for (Animal a: animalList) {
            System.out.println(a.getType() + " " + a.getId());
        }
         */
    }
}
