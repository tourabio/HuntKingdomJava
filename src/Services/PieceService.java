/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.PiecesDefectueuses;
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
public class PieceService {

    Connection cnx2;

    public PieceService() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterPiece(PiecesDefectueuses p) {
        String requete = "INSERT INTO piecesdefectueuses (etat,reserved,nom,categorie,description,image,userid) values(false,false,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getNom());//index starts with 1 for the first value
            pst.setString(2, p.getCategorie());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getImage());
            pst.setInt(5, p.getUserId());
            pst.executeUpdate();
            System.out.println("piece added succesfully ! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<PiecesDefectueuses> afficherPiece() {
        List<PiecesDefectueuses> myList = new ArrayList();
        try {
            Statement st = cnx2.createStatement();
            String requete = "SELECT * FROM piecesdefectueuses";
            //executeQuery seulement pour select 
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                PiecesDefectueuses p = new PiecesDefectueuses();
                p.setId(rs.getInt(1));
                p.setEtat(rs.getBoolean(2));
                p.setReserved(rs.getBoolean(3));
                p.setNom(rs.getString(4));
                p.setCategorie(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setImage(rs.getString(7));
                p.setUserId(rs.getInt(8));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public List<PiecesDefectueuses> afficherPieceNonReserved() {
        List<PiecesDefectueuses> myList = new ArrayList();
        try {
            Statement st = cnx2.createStatement();
            String requete = "SELECT * FROM piecesdefectueuses where reserved=false";
            //executeQuery seulement pour select 
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                PiecesDefectueuses p = new PiecesDefectueuses();
                p.setId(rs.getInt(1));
                p.setEtat(rs.getBoolean(2));
                p.setReserved(rs.getBoolean(3));
                p.setNom(rs.getString(4));
                p.setCategorie(rs.getString(5));
                p.setDescription(rs.getString(6));
                p.setImage(rs.getString(7));
                p.setUserId(rs.getInt(8));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    public void updateReserved(int id) {
        try {
            String requete = "UPDATE piecesdefectueuses SET reserved = true WHERE id = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("product reserved succesfully ! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

}
