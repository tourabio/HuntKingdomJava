/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Produits;
import Services.ProduitService;
import Utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author asus_pc
 */
public class AdminProductController implements Initializable {
 
    @FXML
    TableView<Produits> table;
    @FXML
    TableColumn<Produits, Integer> id;
    @FXML
    TableColumn<Produits, String> lib_prod;
    @FXML
    TableColumn<Produits, Double> prix;
    @FXML
    TableColumn<Produits, Double> prixFinale;
    /*@FXML
    private ImageView imageView;*/
    @FXML
    TableColumn<Produits, String> description;
    @FXML
    TableColumn<Produits, Integer> qte_prod;
    @FXML
    TableColumn<Produits, Date> date_ajout;
    @FXML
    TableColumn<Produits, String> type;
    @FXML
    TableColumn<Produits, String> marque;
    private String absolutePath;
    MyConnection mc = MyConnection.getInstance();
    ProduitService ps = new ProduitService();
    List<Produits> mylist = new ArrayList();
    public ObservableList<Produits> list = FXCollections.observableArrayList(
            ps.afficherProduits()
    );
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
        id.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id"));
        lib_prod.setCellValueFactory(new PropertyValueFactory<Produits, String>("lib_prod"));
        prix.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prix"));
        prixFinale.setCellValueFactory(new PropertyValueFactory<Produits, Double>("prixFinale"));
        description.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        qte_prod.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("qte_prod"));
        date_ajout.setCellValueFactory(new PropertyValueFactory<Produits, Date>("date_ajout"));
        type.setCellValueFactory(new PropertyValueFactory<Produits, String>("type"));
        marque.setCellValueFactory(new PropertyValueFactory<Produits, String>("type"));
        table.setItems(list);
        table.setRowFactory(tv -> {
            TableRow<Produits> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Produits rowData = row.getItem();
                    /**
                     * fill the fields with the selected data *
                     */
                    absolutePath = rowData.getImage();

                  /*  try {
                        Image image = new Image(new FileInputStream(absolutePath));
                        imageView.setImage(image);
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                    }
                    editButton.setVisible(true);*/
                }
            });
            return row;
        });
    }
    public void OnConfirmAction(){
        Produits p = table.getSelectionModel().getSelectedItem();
        if(p==null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate selection");
            alert.setHeaderText(null);
            alert.setContentText("please select a product ! ");
            alert.showAndWait();
        }else{
            System.out.println("table = "+table.getSelectionModel().getSelectedItem());
    }
    }
    
}
