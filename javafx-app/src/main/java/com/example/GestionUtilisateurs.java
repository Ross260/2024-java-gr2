package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateurs {
    private List<Utilisateur> listUser;
    private Connexion link;


    public GestionUtilisateurs(Connexion connexion) {
        this.link = connexion;
    }
    

    public void insererUtilisateur(String nom, String email) {
        String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Insertion de l'utilisateur reussi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur supprimer avec succes.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierUtilisateur(int id, String nom, String email) {
        String sql = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur modifier avec succes.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Email: " + rs.getString("email") +
                        ", Nom: " + rs.getString("nom") +
                        ", Cree le: " + rs.getTimestamp("created_at") +
                        ", Mis à jour le: " + rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> searchUtilisateurByName(String nom) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE nom LIKE ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nom + "%");
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Utilisateur user = new Utilisateur(
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                user.setId(rs.getInt("id"));
                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    

    public List<Utilisateur> searchUtilisateurByEmail(String email) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE email LIKE ?";
        
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + email + "%");
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                Utilisateur user = new Utilisateur(
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                user.setId(rs.getInt("id"));
    
                if (user == null) { // Vérifie si l'objet est null
                    System.err.println("ERREUR : Un utilisateur null a été ajouté !");
                }
    
                utilisateurs.add(user);
            }
    
            System.out.println("Recherche complétée : " + utilisateurs.size() + " utilisateurs trouvés.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Utilisateur user = new Utilisateur(
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                user.setId(rs.getInt("id"));
                utilisateurs.add(user);

                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getTimestamp("created_at"));
                System.out.println(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
}