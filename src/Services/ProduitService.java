/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produits;
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
 * @author asus_pc
 */

public class ProduitService {

    Connection cnx2;

    public ProduitService() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
/*
    public void ajouterProduit(Produits p) {
           String requete = "INSERT INTO produit (nom,categorie,description,image,userid) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getNom());//index starts with 1 for the first value
            pst.setString(2, p.getCategorie());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getImage());
            pst.setInt(5, p.getUserid());
            pst.executeUpdate();
            System.out.println("piece added succesfully ! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/
    public List<Produits> afficherProduits() {
        List<Produits> myList = new ArrayList();
        try {
            Statement st = cnx2.createStatement();
            String requete = "SELECT * FROM produit";
            //executeQuery seulement pour select 
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Produits p = new Produits();
                p.setId(rs.getInt(1));
                p.setPromotion_id(rs.getInt(2));
                p.setLib_prod(rs.getString(3));
                p.setPrix(rs.getDouble(4));
                p.setPrixFinale(rs.getDouble(5));
                p.setDescription(rs.getString(6));
                p.setQte_prod(rs.getInt(7));
                p.setDate_ajout(rs.getDate(8));
                p.setImage(rs.getString(9));
                p.setType(rs.getString(10));
                p.setMarque(rs.getString(11));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

/*
     public void supprimerPersonne(int id) {
        try {
            String requete = "DELETE FROM piecesdefectueuses WHERE id = ?";
               PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, id);//index starts with 1 for the first value
            pst.executeUpdate();
            System.out.println("piece deleted succesfully ! ");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }*/
    /*
     public void modifierPiece(Piecesdefectueuses p) {
        try {
            String requete = "UPDATE piecesdefectueuses SET nom = ?, categorie = ?, description = ?, image = ? WHERE id = ?";
               PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getCategorie()); 
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getImage());
            pst.setInt(5, p.getId());
            pst.executeUpdate();
            System.out.println("piece updated succesfully ! ");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }*/

}

