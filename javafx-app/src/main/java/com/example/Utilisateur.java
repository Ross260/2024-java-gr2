package com.example;

import java.sql.Timestamp;

public class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Utilisateur(String nom, String email, Timestamp created_at, Timestamp updated_at) {
        this.nom = nom;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getCreatedAt() {
        return this.created_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getUpdatedAt() {
        return this.updated_at;
    }
    
    public boolean isValidNom() {
        if (!this.nom.equals("")) {
            return true;
        }

        return false;
    }

    public void __String() {
        System.out.println("Nom: " + this.nom + " " + this.email);
    }
}