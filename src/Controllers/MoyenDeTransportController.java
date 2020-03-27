/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.MoyenDeTransport;
import Services.MoyenDeTransportService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS1
 */
public class MoyenDeTransportController implements Initializable {
    
    @FXML
    private AnchorPane mainpane;
    
    @FXML
    private TableColumn<MoyenDeTransport,String> Type;

    @FXML
    private Button addTransport;

    @FXML
    private TableColumn<MoyenDeTransport,String> Category;

    @FXML
    private TableColumn<MoyenDeTransport,Float> PricePerDay;

    @FXML
    private TableColumn<MoyenDeTransport,String> Mark;

    @FXML
    private TableColumn<MoyenDeTransport,Integer> ID;

    @FXML
    private TableView<MoyenDeTransport> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MoyenDeTransportService ps= new MoyenDeTransportService();
        ArrayList<MoyenDeTransport> a = new ArrayList<>();
        a=(ArrayList<MoyenDeTransport>) ps.afficher();
        ObservableList<MoyenDeTransport> obsl = FXCollections.observableArrayList(a);
       
        table.setItems(obsl);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        PricePerDay.setCellValueFactory(new PropertyValueFactory<>("prixParJour"));
        Category.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        Mark.setCellValueFactory(new PropertyValueFactory<>("marque"));
    }    
    @FXML
    public void goToAdd(ActionEvent event) throws IOException {
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/Gui/addMoyenDeTransport.fxml"));
//        Scene scene = new Scene(root);
//        // scene.getStylesheets().add(getClass().getResource("/Gui/Main.css").toExternalForm());
//        primaryStage.setTitle("Add Accommodation");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        Stage stage = (Stage) addTransport.getScene().getWindow();
//        stage.close();
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/addMoyenDeTransport.fxml"));
          mainpane.getChildren().setAll(pane);
    }
    
}
