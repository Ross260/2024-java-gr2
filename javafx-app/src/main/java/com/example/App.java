package com.example;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Connexion connexion;
    private GestionUtilisateurs gestionUtilisateurs;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        connexion = new Connexion();
        connexion.Connection(); // <-- Initialisation explicite de la connexion
        gestionUtilisateurs = new GestionUtilisateurs(connexion);

        // Fenêtre principale
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        // Boutons principaux
        Button btnListe = new Button("Liste des utilisateurs");
        Button btnAjouter = new Button("Ajouter utilisateur");
        Button btnSupprimer = new Button("Supprimer utilisateur");
        Button btnModifier = new Button("Modifier utilisateur");
        Button btnRechercher = new Button("Rechercher utilisateur");

        btnListe.setMaxWidth(200);
        btnAjouter.setMaxWidth(200);
        btnSupprimer.setMaxWidth(200);
        btnModifier.setMaxWidth(200);
        btnRechercher.setMaxWidth(200);

        // Actions des boutons
        btnListe.setOnAction(e -> ouvrirFenetre("Liste des utilisateurs"));
        btnAjouter.setOnAction(e -> ouvrirFenetre("Ajouter utilisateur"));
        btnSupprimer.setOnAction(e -> ouvrirFenetre("Supprimer utilisateur"));
        btnModifier.setOnAction(e -> ouvrirFenetre("Modifier utilisateur"));
        btnRechercher.setOnAction(e -> ouvrirFenetre("Rechercher utilisateur"));

        root.getChildren().addAll(btnListe, btnAjouter, btnSupprimer, btnModifier, btnRechercher);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des utilisateurs");
        primaryStage.show();
    }

    // Fonction pour ouvrir des fenêtres spécifiques
    private void ouvrirFenetre(String titre) {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Button retour = new Button("Retour");
        retour.setOnAction(e -> stage.close());

        if (titre.equals("Ajouter utilisateur")) {
            Label lblNom = new Label("Nom :");
            TextField fieldNom = new TextField();
            fieldNom.setPromptText("Entrez le nom");

            Label lblEmail = new Label("Email :");
            TextField fieldEmail = new TextField();
            fieldEmail.setPromptText("Entrez l'email");

            Button btnConfirmer = new Button("Confirmer l'ajout");
            btnConfirmer.setOnAction(e -> {
                String nom = fieldNom.getText();
                String email = fieldEmail.getText();
                System.out.println("Nom : " + nom);
                System.out.println("Email : " + email);

                // Appel à la méthode d'ajout en base de données
                gestionUtilisateurs.insererUtilisateur(nom, email);

                fieldNom.clear();
                fieldEmail.clear();
            });

            vbox.getChildren().addAll(
                lblNom, fieldNom,
                lblEmail, fieldEmail,
                btnConfirmer, retour
            );
        }else if (titre.equals("Liste des utilisateurs")) {
            System.out.println("en cours");
        } 
        else {
            Label label = new Label("Fenêtre : " + titre + " (en attente d'implémentation)");
            vbox.getChildren().addAll(label, retour);
        }

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.setTitle(titre);
        stage.show();
    }

    // Fermeture propre de la connexion à la base
    @Override
    public void stop() throws Exception {
        super.stop();
        connexion.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
