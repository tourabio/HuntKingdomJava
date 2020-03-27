/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hebergement;
import Services.HebergementService;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS1
 */
public class AddHebergementController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField prixParJour;

    @FXML
    private TextField image;

    @FXML
    private TextField capacite;

    @FXML
    private TextField adresse;
    
    @FXML
    private TextArea description;

    @FXML
    private ComboBox<String> type;
    ObservableList<String>list = FXCollections.observableArrayList("Caravane","flat","house");

    @FXML
    private TextField nbChambre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(list);
    }    
    public void AddAccommodation(ActionEvent event) throws IOException{
    MyConnection mc =  MyConnection.getInstance();
    HebergementService ps = new HebergementService();
    float price=Float.parseFloat(prixParJour.getText());
    int nbch=Integer.parseInt(nbChambre.getText());
    int nblits=Integer.parseInt(capacite.getText());
    Hebergement h = new Hebergement(type.getValue(),price,image.getText(),adresse.getText(),nbch,nblits,nblits,description.getText());
    ps.addHebergement(h);

    }
}