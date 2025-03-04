package com.monprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
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
}

