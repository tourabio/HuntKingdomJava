/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hebergement;
import Entities.MoyenDeTransport;
import Services.HebergementService;
import Services.MoyenDeTransportService;
import Utils.MyConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS1
 */
public class AddMoyenDeTransportController implements Initializable {
    
    @FXML
    private Button add;

    @FXML
    private TextField image;

    @FXML
    private TextField prixParJour;

    @FXML
    private TextField categorie;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField marque;
    ObservableList<String>list = FXCollections.observableArrayList("battery","gasoline","diesel");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(list);
    }  
    
    public void AddTransport(ActionEvent event) {
        MyConnection mc =  MyConnection.getInstance();
    MoyenDeTransportService ps = new MoyenDeTransportService();
    float price=Float.parseFloat(prixParJour.getText());
    MoyenDeTransport h = new MoyenDeTransport(type.getValue(),price,image.getText(),marque.getText(),categorie.getText());
    ps.addMoyenDeTransport(h);
    }
    
}
