package example.controller;


import example.Database.DAO;
import example.Database.JpaDAO;
import example.model.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label horseCount;

    @FXML
    private Label cowCount;

    @FXML
    private Label pigCount;

    @FXML
    private Label goatCount;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        DAO dao = new JpaDAO();
        List<Animal> animalList = dao.getAnimals();
        Map<String, Integer> animalCount = new HashMap<>();

        // egyes állatok számának lekérése
        for (Animal animal:animalList) {
            int count = animalCount.getOrDefault(animal.getType(), 0);
            animalCount.put(animal.getType(), count + 1);
        }

        // labelek beállítása
        horseCount.setText(String.valueOf(animalCount.get("Horse")));
        cowCount.setText(String.valueOf(animalCount.get("Cow")));
        pigCount.setText(String.valueOf(animalCount.get("Pig")));
        goatCount.setText(String.valueOf(animalCount.get("Goat")));


        // pie chart szeletek beállítása
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Horse", animalCount.get("Horse")),
                new PieChart.Data("Cow", animalCount.get("Cow")),
                new PieChart.Data("Pig", animalCount.get("Pig")),
                new PieChart.Data("Goat", animalCount.get("Goat")));

        // piechart tulajdonságok beállítása
        pieChart.dataProperty().setValue(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(90);

        // visszalépés a mainWindow-ba
        backButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/mainWindow.fxml", rootPane));

    }


}
