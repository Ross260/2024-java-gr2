
package com.monprojet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

 /*
         * 1- Ajouter un utilisateur dans la table
         * 2- Lister les utilisateur de la table
         * 3- Supprimer un utilisateur par son id
         * 4- Edit d'un utilisateur par son id
         * 5- rechercher un utilisateur par son mail et son nom
         * 
         */
        
        
        
public class GestionUtilisateur {
    ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

    public void listUtilisateurs () {
        Connexion connexion = new Connexion();
        
        // Utilisation du try-with-resources pour gérer la fermeture automatique des ressources
        try (Connection conn = connexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nom, email,created_at,update_at FROM utilisateurs")) {

            System.out.println("Liste des utilisateurs :");
            // Parcours du ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                Timestamp timestam = rs.getTimestamp("created_at");
                Timestamp update_table = rs.getTimestamp("update_at");
                System.out.println("ID : " + id + ", Nom : " + nom + ", Email : " + email + "  Timestamp: " + timestam + " Update_table " + update_table);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
        }
    }

    public void addUtilisateurs(Utilisateur utilisateur) {
        // Connexion à la BD

        Connexion connexion = new Connexion();
        String sql = "INSERT INTO utilisateurs (nom, email, created_at, update_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = connexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // stmt.setInt(1, utilisateur.getId());
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setObject(3, utilisateur.getCreatedAt());
            stmt.setObject(4, utilisateur.getUpdatedAt());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                utilisateurs.add(utilisateur); // Ajout à la liste
                System.out.println("✅ Utilisateur ajouté avec succès : " + utilisateur);
            } else {
                System.out.println("⚠️ Échec de l'ajout de l'utilisateur.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
}
