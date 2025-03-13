package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConvertToCsv {

    public static void convertirListeUtilisateursEnCsv(List<Utilisateur> utilisateurs, String cheminFichier) {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            // Écriture de l'en-tête du fichier CSV
            writer.append("ID,Nom,Email,Créé le,Mis à jour le\n");

            // Écriture des données utilisateurs
            for (Utilisateur user : utilisateurs) {
                writer.append(user.getId() + ",")
                      .append(user.getNom() + ",")
                      .append(user.getEmail() + ",")
                      .append(user.getCreatedAt() + ",")
                      .append(user.getUpdatedAt() + "\n");
            }

            System.out.println("✅ Fichier CSV généré avec succès : " + cheminFichier);

        } catch (IOException e) {
            System.err.println("❌ Erreur lors de la création du fichier CSV : " + e.getMessage());
        }
    }
}
