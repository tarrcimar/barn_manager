package example.controller;

import com.jfoenix.controls.JFXListCell;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import example.model.Animal;
import javafx.fxml.FXML;
import example.model.GenderType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import example.Database.JpaAnimalDAO;
import javafx.scene.layout.AnchorPane;

public class ListCellController extends JFXListCell<Animal> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView removeFromList;

    @FXML
    private ImageView fixListUnit;

    @FXML
    private TextField namefield;

    @FXML
    private TextField genderfield;

    @FXML
    private TextField typefield;

    @FXML
    private TextField datefield;

    @FXML
    private TextField activityfield;

    @FXML
    private TextField commentField;

    @FXML
    private AnchorPane rootAnchorPane;

    private FXMLLoader fxmlLoader;

    private void editButtonClicked(Animal myAnimal){
        String animalname = namefield.getText();
        String gender = genderfield.getText();
        String type = typefield.getText();
        int activity = Integer.parseInt(activityfield.getText());
        String comment = commentField.getText();

        GenderType gendertype = GenderType.FEMALE;
        if (gender == "MALE")
        {
            gendertype = GenderType.MALE;
        }
        else if(gender == "FEMALE")
        {
          gendertype = GenderType.FEMALE;
        }
        myAnimal.setGender(gendertype);

        myAnimal.setType(type);
        myAnimal.setActivity(activity);
        myAnimal.setComment(comment);
        JpaAnimalDAO janos = new JpaAnimalDAO();
        janos.updateAnimal(myAnimal);
    }
    private void removeButtonClicked(Animal myAnimal){
        JpaAnimalDAO jozsef = new JpaAnimalDAO();
        jozsef.deleteAnimal(myAnimal);
    }

    @FXML
    void initialize() {


    }
    @Override
    protected void updateItem(Animal myAnimal, boolean empty) {
        super.updateItem(myAnimal, empty);

        if(empty || myAnimal == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/example/view/listcell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //gomb interakciók
            removeFromList.setOnMouseClicked(event -> removeButtonClicked(myAnimal));
            fixListUnit.setOnMouseClicked(event -> editButtonClicked(myAnimal));

            setText(null);
            setGraphic(rootAnchorPane);
        }

    }
}
