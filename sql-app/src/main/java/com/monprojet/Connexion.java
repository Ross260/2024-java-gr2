package com.monprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String url = "jdbc:mysql://localhost:3306/mabasegrp2"; 
    private static final String utilisateur = "root";
    private static final String motDePasse = "";
    Connection connexion = null;


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, utilisateur, motDePasse);
    }
}

