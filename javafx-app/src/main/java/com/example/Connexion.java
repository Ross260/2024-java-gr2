package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String url = "jdbc:mysql://localhost:3306/mabasegrp2"; 
    private static final String utilisateur = "root";
    private static final String motDePasse = "";
    Connection connexion ;


    public Connection Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    
        return this.connexion;
    }
    
    

    public void close() throws SQLException {
        if (connexion != null && !connexion.isClosed()) {
            connexion.close();
        }
    }
}


// package com.monprojet;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class Connexion {
//     // Informations de connexion
//     String url = "jdbc:mysql://localhost:3306/mabasegrp2"; // Remplacer "maBase" par le nom de votre base
//     String utilisateur = "root";
//     String motDePasse = "root";
//     Connection connexion = null;

//     public Connexion () {
//         try {
//             // Établir la connexion
//             this.connexion = DriverManager.getConnection(this.url, this.utilisateur, this.motDePasse);
//             System.out.println("Connexion réussie !");
            
//         } catch (SQLException e) {
//             System.out.println("Erreur de connexion : " + e.getMessage());
//         }
//     }

//     public void close() {
//         if (this.connexion != null) { 
//             try { 
//                 this.connexion.close(); 
//                 System.out.println("Connexion fermée avec succès."); 
//             } catch (SQLException e) { 
//                 System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage()); 
//             } 
//         } 
//     }
// }