package example.controller;

import com.jfoenix.controls.JFXListCell;
import example.Database.JpaForageDAO;
import example.model.Forage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
        //a 3 gombhoz magyarázat ha ráviszi a kurzort az ember
        Tooltip.install(addButton, new Tooltip("Add"));
        Tooltip.install(deductButton, new Tooltip("Deduct"));
        Tooltip.install(deleteButton, new Tooltip("Delete"));
    }

    //kitörli az adott takarmányt a databaseből
    public void deleteButtonClicked(Forage myForage){
        getListView().getItems().remove(getItem());
        JpaForageDAO all = new JpaForageDAO();
        all.deleteForageByIds(myForage.getOwner_id().getId(),myForage.getId());
    }

    public void addButtonClicked(Forage myForage){
        //megnézzük hogy írt-e be valamit a user, ha nem akkor azt 0-nak vesszük
        int number = inputTextArea.getText()==""?0:Integer.parseInt(inputTextArea.getText());
        //0-t meg negatív számot ne adjon már hozzá a felhasználó, error
        if(number<=0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Numeric error");
            a.setHeaderText("Numeric error");
            a.setContentText("The number must be greater than 0.");
            a.show();
        }else{
            //ha nagyobb mint 0 akkor hozzáadja az adott értéket a meglévőhőz
            JpaForageDAO jfdao = new JpaForageDAO();
            myForage.setAmount(myForage.getAmount()+number);
            jfdao.updateForage(myForage);
            //frissíti a mennyiség labelt -- akinek van kedve csinálja meg property bindolással
            quantityLabel.setText(myForage.getAmount().toString());
        }
    }

    public void deductButtonClicked(Forage myForage){
        //megnézzük hogy írt-e be valamit a user, ha nem akkor azt 0-nak vesszük
        int number = inputTextArea.getText()==""?0:Integer.parseInt(inputTextArea.getText());
        //0-t meg negatív számot ne vonjon már ki a felhasználó, error
        if(number<=0){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Numeric error");
            a.setHeaderText("Numeric error");
            a.setContentText("The number must be greater than 0.");
            a.show();
        }else{
            //ha többet von ki mennyiségből mint amennyi van akkor már kitöröljük az egész takarmányt
            if(number>myForage.getAmount()){
                deleteButtonClicked(myForage);
            }
            else {
                //kivonja az értéket a meglévőből,frissíti a databaset
                JpaForageDAO jfdao = new JpaForageDAO();
                myForage.setAmount(myForage.getAmount() - number);
                jfdao.updateForage(myForage);
                //firssíti a mennyiség labelt -- akinek van kedve csinálja meg property bindolással
                quantityLabel.setText(myForage.getAmount().toString());
            }
        }
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

            //labelek beállítása
            switch (myForage.getName()){
                case HAY:
                    foodNameLabel.setText("Hay");
                    break;
                case WORM:
                    foodNameLabel.setText("Worm");
                    break;
                case MEAT:
                    foodNameLabel.setText("Meat");
                    break;
                case ROOT:
                    foodNameLabel.setText("Root");
                    break;
                case FUNGUS:
                    foodNameLabel.setText("Fungus");
                    break;
                case SEED:
                    foodNameLabel.setText("Seed");
                    break;
            }
            switch (myForage.getUnit()){
                case KG:
                    unitLabel.setText("Kg");
                    break;
                case TON:
                    unitLabel.setText("Ton");
                    break;
                case BOX:
                    unitLabel.setText("Box");
                    break;
            }
            quantityLabel.setText(myForage.getAmount().toString());

            //gomb interakciók
            deleteButton.setOnMouseClicked(event -> deleteButtonClicked(myForage));
            addButton.setOnMouseClicked(event -> addButtonClicked(myForage));
            deductButton.setOnMouseClicked(event -> deductButtonClicked(myForage));

            setText(null);
            setGraphic(rootAnchorPane);
        }

        }
    }
