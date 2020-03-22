/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import huntkingdom.HuntKingdom;
import java.io.IOException;
import java.net.URL;

import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author tibh
 */
public class HomeController implements Initializable {

  
    @FXML
    private Button btnanimals;
    @FXML
    private Button btnevents;
    @FXML
    private Button btnshop;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnservices;
    @FXML
    private Button btntraining;
    @FXML
    private Button btnreparation;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!HuntKingdom.isSplasheded)
        {
                    loadSplashScreen();
        }
        img.setImage(new Image("/Uploads/back.jpg"));
      
    }    

    @FXML
    private void btnanimalsAction(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void btneventsAction(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Event.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void btnshopAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Shop.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void btnhomeAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void btnservicesAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Service.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void btntrainingAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Training.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void btnreparationAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Reparation.fxml"));
        mainpane.getChildren().setAll(pane);
    }
    private void loadSplashScreen() {
    try {
        HuntKingdom.isSplasheded=true;
        //Load splash screen view FXML
        StackPane panee = FXMLLoader.load(getClass().getResource(("/Gui/welcome.fxml")));
        
        //Add it to root container (Can be StackPane, AnchorPane etc)
        mainPane.getChildren().setAll(panee);
 
        //Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), panee);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
 
        //Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), panee);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
 
        fadeIn.play();
 
        //After fade in, start fade out
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });
 
        //After fade out, load actual content
        fadeOut.setOnFinished((e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
                 mainPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } catch (IOException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
