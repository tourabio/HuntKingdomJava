/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Animal;
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
 * @author tibh
 */
public class AnimalService {
  Connection cnx2;
    public AnimalService(){
    cnx2 = MyConnection.getInstance().getCnx();
    }  
    public List<Animal> afficher()
    {
         List<Animal> myList= new ArrayList();
        try {
           
            String requete = "SELECT * FROM Animal ";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Animal a = new Animal();
                a.setId(rs.getInt(1));
                a.setCategorie(rs.getString(2));
                a.setNom(rs.getString(3));
                a.setDescription(rs.getString(4));
                a.setImage_animal(rs.getString(5));
                a.setDebutSaison(rs.getInt(6));
                a.setFinSaison(rs.getInt(7));
                myList.add(a);
                
            }
                    
                   
        } catch (SQLException ex) {
            System.err.println("error d affichage");
          
        }    
             return myList;
    }
}
