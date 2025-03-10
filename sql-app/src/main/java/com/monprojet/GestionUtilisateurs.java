
// package com.monprojet;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.sql.Timestamp;
// import java.util.ArrayList;

//     /*
//          * 1- Ajouter un utilisateur dans la table
//          * 2- Lister les utilisateur de la table
//          * 3- Supprimer un utilisateur par son id
//          * 4- Edit d'un utilisateur par son id
//          * 5- rechercher un utilisateur par son mail et son nom
//          * 
//     */
        
// public class GestionUtilisateur {
//     ArrayList<Utilisateur> utilisateur = new ArrayList<Utilisateur>();

//     public void listUtilisateurs () {
//         Connexion connexion = new Connexion();
        
//         // Utilisation du try-with-resources pour gérer la fermeture automatique des ressources
//         try (Connection conn = connexion.Connection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT id, nom, email,created_at,update_at FROM utilisateur")) {

//             System.out.println("Liste des utilisateurs :");
//             // Parcours du ResultSet
//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 String nom = rs.getString("nom");
//                 String email = rs.getString("email");
//                 Timestamp timestam = rs.getTimestamp("created_at");
//                 Timestamp update_table = rs.getTimestamp("update_at");
//                 System.out.println("ID : " + id + ", Nom : " + nom + ", Email : " + email + "  Timestamp: " + timestam + " Update_table " + update_table);
//             }
//         } catch (SQLException e) {
//             System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
//         }
//     }

//     public void addUtilisateurs(Utilisateur utilisateur) {
//         // Connexion à la BD

//         Connexion connexion = new Connexion();
//         String sql = "INSERT INTO utilisateurs (nom, email, created_at, update_at) VALUES (?, ?, ?, ?)";

//         try (Connection conn = connexion.Connection();
//              PreparedStatement stmt = conn.prepareStatement(sql)) {

//             // stmt.setInt(1, utilisateur.getId());
//             stmt.setString(1, utilisateur.getNom());
//             stmt.setString(2, utilisateur.getEmail());
//             stmt.setObject(3, utilisateur.getCreatedAt());
//             stmt.setObject(4, utilisateur.getUpdatedAt());

//             int rowsInserted = stmt.executeUpdate();
//             if (rowsInserted > 0) {
//                 utilisateurs.add(utilisateur); // Ajout à la liste
//                 System.out.println("Utilisateur ajouté avec succès : " + utilisateur);
//             } else {
//                 System.out.println(" Échec de l'ajout de l'utilisateur.");
//             }

//         } catch (SQLException e) {
//             System.err.println("❌ Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
//         }
//     }

//     public ArrayList<Utilisateur> getUtilisateurs() {
//         return utilisateurs;
//     }
// }



package com.monprojet;

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
        this.listUser = new ArrayList<>();
        this.link = connexion;
    }

    public void insererUtilisateur(String nom, String email) {
        String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Insertion de l'utilisateur réussi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(int id) {
        String sql = "DELETE FROM utilisateur WHERE id = ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès.");
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
            System.out.println("Utilisateur modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherUtilisateurs() {
        String sql = "SELECT * FROM utilisateur";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Email: " + rs.getString("email") +
                        ", Nom: " + rs.getString("nom") +
                        ", Créé le: " + rs.getTimestamp("created_at") +
                        ", Mis à jour le: " + rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rechercherUtilisateur(String motClef) {
        String sql = "SELECT * FROM utilisateur WHERE nom LIKE ? OR email LIKE ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + motClef + "%");
            pstmt.setString(2, "%" + motClef + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Email: " + rs.getString("email") +
                        ", Nom: " + rs.getString("nom") +
                        ", Créé le: " + rs.getTimestamp("created_at") +
                        ", Mis à jour le: " + rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//     public void toCSV() {
//         String csvFile = "chemin du csv";
//         String sql = "SELECT * FROM utilisateur";

//         try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
//              ResultSet rs = pstmt.executeQuery();
//              FileWriter writer = new FileWriter(csvFile)) {

//             // Écrire l'en-tête du fichier CSV
//             writer.append("ID,Nom,Email,Cree le,Mis a jour le\n");

//             // Parcourir les résultats et écrire chaque ligne dans le fichier CSV
//             while (rs.next()) {
//                 writer.append(String.valueOf(rs.getInt("id"))).append(",");
//                 writer.append(rs.getString("nom")).append(",");
//                 writer.append(rs.getString("email")).append(",");
//                 writer.append(rs.getTimestamp("created_at").toString()).append(",");
//                 writer.append(rs.getTimestamp("updated_at").toString()).append("\n");
//             }

//             System.out.println("Exportation vers CSV terminée. Fichier sauvegardé à : " + csvFile);

//         } catch (SQLException | IOException e) {
//             e.printStackTrace();
//         }
//     }
}