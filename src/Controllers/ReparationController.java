/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.PiecesDefectueuses;
import Services.PieceService;
import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author tibh
 */
public class ReparationController implements Initializable {

    @FXML
    private StackPane stack;
     @FXML
    private StackPane stack2;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private JFXTextArea description;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView listView;
    @FXML
    private JFXTabPane tabPane;
    private String absolutePath = "";
    
    @FXML
    private FlowPane flow;
    @FXML
    private Pane listPane;
    ObservableList<String> list = FXCollections.observableArrayList("Hunting", "Fishing");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorie.setItems(list);
        PieceService ps= new PieceService();
        /*signalAnnonceService = new SignalAnnonceService();
        signalAnnonceService.nbSignalParAnnonce();
        CardsAnnonceController.i = 0;*/
        ArrayList<PiecesDefectueuses> pieces = new ArrayList<>();
        pieces = (ArrayList) ps.afficherPiece();
        ObservableList<PiecesDefectueuses> obsl = FXCollections.observableArrayList(pieces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        //indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            if(pieces.get(i).isReserved()){
            try {    
                
                FXMLLoader loader = new FXMLLoader();
               
                Pane root = loader.load(getClass().getResource("/Gui/SinglePiece.fxml").openStream());
                 SinglePieceController single = (SinglePieceController)loader.getController();
                 single.getInfo(pieces.get(i));
                 if(pieces.get(i).isEtat()){
                 JFXButton button = single.getButton();
                 button.setText("Ready");
                 button.setStyle("-fx-background-color: green;");
                 
                 button.setOnAction(e->{
                     
                    // System.out.println("test");
                 JFXDialogLayout message = new JFXDialogLayout();
                 message.setHeading(new Text("Details!"));
                message.setBody(new Text("your piece is ready !"));
                JFXDialog msg = new JFXDialog(stack2, message, JFXDialog.DialogTransition.CENTER);
                JFXButton buttonClose = new JFXButton("close");
                buttonClose.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        msg.close();
                    }
                });
                message.setActions(buttonClose);
                msg.show();
                 });
                 }
                nodes[i]=root;
                //nodes[i] = FXMLLoader.load(getClass().getResource("/Gui/SinglePiece.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
       
     
        
    }
 
        
        
        
        

    

    private void copyFile(File file) {
        try {
            File dest = new File("E:\\roadToInfini\\java\\projet\\final\\HuntKingdom\\src\\Uploads\\" + file.getName()); //any location
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.setTitle("Select an image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getName());
            copyFile(selectedFile);
            absolutePath = selectedFile.getAbsolutePath();
        } else {
            System.out.println("file is not valid !");
        }
        try {
            Image image = new Image(new FileInputStream(absolutePath));
            imageView.setImage(image);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public boolean validateFields() {
        if (nom.getText().isEmpty() || description.getText().isEmpty() || absolutePath.equals("") || categorie.getValue() == null) {
            /*Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("please enter all the information ! ");
        alert.showAndWait();*/
            JFXDialogLayout message = new JFXDialogLayout();
            message.setHeading(new Text("error!"));
            message.setBody(new Text("please enter all the information !"));
            JFXDialog msg = new JFXDialog(stack, message, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("close");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    msg.close();
                }
            });
            message.setActions(button);
            msg.show();

            return false;
        }

        return true;
    }

    @FXML
    void addPiece(ActionEvent event) {
        if (validateFields()) {
            MyConnection mc = MyConnection.getInstance();
            PieceService ps = new PieceService();
            PiecesDefectueuses p = new PiecesDefectueuses(nom.getText(), categorie.getValue(), description.getText(), absolutePath, 1);
            ps.ajouterPiece(p);
            System.out.println(p);
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            
            selectionModel.select(1); //select by index starting with 0

        }
    }

}
