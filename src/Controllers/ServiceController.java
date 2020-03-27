/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entities.Hebergement;
import Services.HebergementService;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author tibh
 */
public class ServiceController implements Initializable {
    
    @FXML
    private AnchorPane mainpane;
    
    @FXML
    private Button addHebergement;
    
    @FXML
    private TableColumn<Hebergement, String> Category;

    @FXML
    private TableColumn<Hebergement, String> Address;

    @FXML
    private TableColumn<Hebergement, String> Capacity;

    @FXML
    private TableColumn<Hebergement, Float> PricePerDay;

    @FXML
    private TableColumn<Hebergement, Integer> ID;

    @FXML
    private TableColumn<Hebergement, Integer> NbRooms;

    @FXML
    private TableColumn<Hebergement, String> Description;

    @FXML
    private TableView<Hebergement> table;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HebergementService ps= new HebergementService();
        ArrayList<Hebergement> a = new ArrayList<>();
        a=(ArrayList<Hebergement>) ps.afficher();
        ObservableList<Hebergement> obsl = FXCollections.observableArrayList(a);
       
        table.setItems(obsl);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Category.setCellValueFactory(new PropertyValueFactory<>("type"));
        PricePerDay.setCellValueFactory(new PropertyValueFactory<>("prixParJour"));
        Address.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        NbRooms.setCellValueFactory(new PropertyValueFactory<>("nbChambre"));
        Capacity.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }    
    public void goToAdd(ActionEvent event) throws IOException {
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/Gui/addHebergement.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Add Accommodation");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        Stage stage = (Stage) table.getScene().getWindow();
//        stage.close();
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/addHebergement.fxml"));
          mainpane.getChildren().setAll(pane);
    }
}
