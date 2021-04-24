package example.controller;

import com.jfoenix.controls.JFXListCell;
import example.Database.JpaForageDAO;
import example.model.Forage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ForageCellController extends JFXListCell<Forage> {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label foodNameLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label unitLabel;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private ImageView addButton;

    @FXML
    private ImageView deductButton;

    @FXML
    private ImageView deleteButton;

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize(){
        Tooltip.install(addButton, new Tooltip("Add"));
        Tooltip.install(deductButton, new Tooltip("Deduct"));
        Tooltip.install(deleteButton, new Tooltip("Delete"));
    }

    @Override
    protected void updateItem(Forage myForage, boolean empty) {
        super.updateItem(myForage, empty);

        if(empty || myForage == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/example/view/forageCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            foodNameLabel.setText(myForage.getName().toString());
            quantityLabel.setText(myForage.getAmount().toString());
            unitLabel.setText(myForage.getUnit().toString());

            deleteButton.setOnMouseClicked(event -> {
                getListView().getItems().remove(getItem());
                JpaForageDAO all = new JpaForageDAO();
                all.deleteForageByIds(myForage.getOwner_id().getId(),myForage.getId());
            });

            setText(null);
            setGraphic(rootAnchorPane);
        }
    }
}
