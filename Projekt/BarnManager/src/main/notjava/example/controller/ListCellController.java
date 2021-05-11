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
import javafx.scene.image.ImageView;
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
        int activity = Integer.parseInt(activityfield.getText());
        String comment = commentField.getText();
        myAnimal.setActivity(activity);
        myAnimal.setComment(comment);
        JpaAnimalDAO janos = new JpaAnimalDAO();
        janos.updateAnimal(myAnimal);
        //getListView().getItems().remove(getItem());
        //getListView().getItems().add(myAnimal);
    }
    private void removeButtonClicked(Animal myAnimal){
        JpaAnimalDAO jozsef = new JpaAnimalDAO();
        jozsef.deleteAnimalByIds(myAnimal.getBarn().getId(), myAnimal.getId());
        getListView().getItems().remove(getItem());
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

            namefield.setEditable(false);
            namefield.setText(myAnimal.getId().toString());
            genderfield.setEditable(false);
            genderfield.setText(myAnimal.getGender().toString());
            typefield.setEditable(false);
            typefield.setText(myAnimal.getType());
            datefield.setEditable(false);
            if (myAnimal.getAddedOn() != null)
            {
                datefield.setText(myAnimal.getAddedOn().toString());
            }
            if (myAnimal.getActivity() != null) {
                activityfield.setText(myAnimal.getActivity().toString());
            }
            if (myAnimal.getComment() != null) {
                commentField.setText(myAnimal.getComment());
            }
            //gomb interakciók
            removeFromList.setOnMouseClicked(event -> removeButtonClicked(myAnimal));
            fixListUnit.setOnMouseClicked(event -> editButtonClicked(myAnimal));

            setText(null);
            setGraphic(rootAnchorPane);
        }

    }
}
