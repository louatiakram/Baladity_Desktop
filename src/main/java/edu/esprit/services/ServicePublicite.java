package edu.esprit.services;

import edu.esprit.entities.Publicite;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;


public class ServicePublicite implements IService<Publicite> {
        Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void ajouter(Publicite publicite) {

        String req = "INSERT INTO `publicite`(`titre_pub`, `description_pub`, `contact_pub`, `localisation_pub`, `image_pub`,`id_user`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, publicite.getTitre_pub());
            ps.setString(2, publicite.getDescription_pub());
            ps.setInt(3, publicite.getContact_pub());
            ps.setString(4, publicite.getLocalisation_pub());
            ps.setString(5, publicite.getImage_pub());
            ps.setInt(6, publicite.getId_user());
            ps.executeUpdate();
            System.out.println("publicite added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Publicite publicite) {
        // Check if the specified ID exists before attempting to update
        if (publiciteExists(publicite.getId_pub())) {
            String req = "UPDATE `publicite` SET `titre_pub`=?, `description_pub`=?, `contact_pub`=?, `localisation_pub`=? , `image_pub` , `id_user`=?WHERE `id_pub`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, publicite.getTitre_pub());
                ps.setString(2, publicite.getDescription_pub());
                ps.setInt(3, publicite.getContact_pub());
                ps.setString(4, publicite.getLocalisation_pub());
                ps.setInt(5, publicite.getId_pub());
                ps.setString(6, publicite.getImage_pub());
                ps.setInt(7, publicite.getId_user());
                ps.executeUpdate();
                System.out.println("Publicite with ID " + publicite.getId_pub() + " modified!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Publicite with ID " + publicite.getId_pub() + " does not exist.");
        }
    }
    private boolean publiciteExists(int id_pub) {
        String req = "SELECT COUNT(*) FROM `publicite` WHERE `id_pub`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_pub);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Returns true if the ID exists
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // Default to false in case of an exception
    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Publicite> getAll() {
        return null;
    }

    @Override
    public Publicite getOneByID(int id) {
        return null;
    }
}
