package example.controller;


import com.jfoenix.controls.JFXButton;
import example.Database.DAO;
import example.Database.ForageDAO;
import example.Database.JpaDAO;
import example.Database.JpaForageDAO;
import example.model.Animal;
import example.model.Barn;
import example.model.Forage;
import example.model.MeasurementUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class ChartController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private PieChart animalsChart;

    @FXML
    private Label horseCount;

    @FXML
    private Label cowCount;

    @FXML
    private Label pigCount;

    @FXML
    private Label goatCount;

    @FXML
    private JFXButton backButton;

    @FXML
    private Label hayCount;

    @FXML
    private Label wormCount;

    @FXML
    private Label meatCount;

    @FXML
    private Label rootCount;

    @FXML
    private Label fungusCount;

    @FXML
    private Label seedCount;

    @FXML
    private PieChart foragesChart;

    @FXML
    void initialize() {
        DAO dao = new JpaDAO();
        JpaForageDAO all = new JpaForageDAO();

        // lekérjük a userhez tartozó istállókat
        List<Barn> barnListByUserId = dao.getBarnsByUserId(MainWindowController.userId);
        List<Forage> forageList = all.getForagesByUserId(MainWindowController.userId);
        Map<String, Long> forageCount = new HashMap<>();

        List<Animal> animalList;
        Map<String, Integer> animalCount = new HashMap<>();

        // egyes állatok számának lekérése
        for(Barn barn: barnListByUserId) {
            animalList = dao.getAnimalsByBarnId(barn.getId());
            for (Animal animal : animalList) {
                int count = animalCount.getOrDefault(animal.getType(), 0);
                animalCount.put(animal.getType(), count + 1);
            }
            animalList.clear();
        }

        // labelek beállítása
        horseCount.setText(String.valueOf(animalCount.getOrDefault("Horse", 0)));
        cowCount.setText(String.valueOf(animalCount.getOrDefault("Cow", 0)));
        pigCount.setText(String.valueOf(animalCount.getOrDefault("Pig", 0)));
        goatCount.setText(String.valueOf(animalCount.getOrDefault("Goat", 0)));


        // pie chart szeletek beállítása
        ObservableList<PieChart.Data> pieChartDataAnimal = FXCollections.observableArrayList(
                new PieChart.Data("Horse", animalCount.getOrDefault("Horse", 0)),
                new PieChart.Data("Cow", animalCount.getOrDefault("Cow", 0)),
                new PieChart.Data("Pig", animalCount.getOrDefault("Pig", 0)),
                new PieChart.Data("Goat", animalCount.getOrDefault("Goat", 0)));

        // Állatok piechart tulajdonságok beállítása
        animalsChart.dataProperty().setValue(pieChartDataAnimal);
        animalsChart.setClockwise(true);
        animalsChart.setLabelsVisible(false);
        animalsChart.setStartAngle(90);

        //----------------------------------------------
        // Termény vizualizáció

        // BOX, TON - ról konvertálás KG-be
        for (Forage forage:forageList) {
            MeasurementUnit forageType = forage.getUnit();
            long amount = 0;

            switch (forageType){
                case KG:
                    amount = forage.getAmount();
                    break;
                case BOX:
                    amount = forage.getAmount() * 40;
                    break;
                case TON:
                    amount = forage.getAmount() * 1000;
                    break;
            }
            forageCount.put(String.valueOf(forage.getName()), amount);
        }

        hayCount.setText(forageCount.getOrDefault("HAY", (long) 0) + "KG");
        wormCount.setText(forageCount.getOrDefault("WORM", (long) 0) + "KG");
        meatCount.setText(forageCount.getOrDefault("MEAT", (long) 0) + "KG");
        rootCount.setText(forageCount.getOrDefault("ROOT", (long) 0) + "KG");
        fungusCount.setText(forageCount.getOrDefault("FUNGUS", (long) 0) + "KG");
        seedCount.setText(forageCount.getOrDefault("SEED", (long) 0) + "KG");

        ObservableList<PieChart.Data> pieChartDataForage = FXCollections.observableArrayList(
                new PieChart.Data("Hay", forageCount.getOrDefault("HAY", (long)0)),
                new PieChart.Data("Worm", forageCount.getOrDefault("WORM", (long)0)),
                new PieChart.Data("Meat", forageCount.getOrDefault("MEAT", (long)0)),
                new PieChart.Data("Root", forageCount.getOrDefault("ROOT", (long)0)),
                new PieChart.Data("Fungus", forageCount.getOrDefault("FUNGUS", (long)0)),
                new PieChart.Data("Seed", forageCount.getOrDefault("SEED", (long) 0)));


        foragesChart.dataProperty().setValue(pieChartDataForage);
        foragesChart.setClockwise(true);
        foragesChart.setLabelsVisible(false);
        foragesChart.setStartAngle(90);

        // visszalépés a mainWindow-ba
        backButton.setOnAction(actionEvent -> new FadeController().fadeOut("/example/view/mainWindow.fxml", rootPane));

    }


}
