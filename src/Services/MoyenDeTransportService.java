/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Hebergement;
import Entities.MoyenDeTransport;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS1
 */
public class MoyenDeTransportService {
    Connection cnx2;
    public MoyenDeTransportService(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    public List<MoyenDeTransport> afficher()
    {
         List<MoyenDeTransport> myList= new ArrayList();
        try {
           
            String requete = "SELECT * FROM Moyen_De_Transport ";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                MoyenDeTransport a = new MoyenDeTransport();
                a.setId(rs.getInt(1));
                a.setType(rs.getString(2));
                a.setPrixParJour(rs.getFloat(3));
                a.setMarque(rs.getString(4));
                a.setCategorie(rs.getString(5));
                a.setImage(rs.getString(6));
                myList.add(a);
                
            }
                    
                   
        } catch (SQLException ex) {
            System.err.println("error d affichage");
          
        }    
             return myList;
    }
    public void addMoyenDeTransport(MoyenDeTransport h){
        try {
            String requete="INSERT INTO moyen_de_transport(type,prixParJour,image,categorie,marque) "
                    + "values(?,?,?,?,?)";
            PreparedStatement pst=cnx2.prepareStatement(requete);
            pst.setString(1, h.getType());
            pst.setFloat(2, h.getPrixParJour());
            pst.setString(3, h.getImage());
            pst.setString(4, h.getCategorie());
            pst.setString(5, h.getMarque());
            pst.executeUpdate();
            System.out.println("Mean of transport succesfully added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
