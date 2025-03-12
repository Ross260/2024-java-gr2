package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Timestamp;



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
    @SuppressWarnings("unchecked")
    private void ouvrirFenetre(String titre) {
        final Stage stage = new Stage();
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
            Stage stageListe = new Stage();
            stageListe.setTitle("Liste des utilisateurs");

            // Création TableView
            TableView<Utilisateur> tableView = new TableView<>();

            TableColumn<Utilisateur, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<Utilisateur, String> colEmail = new TableColumn<>("Email");
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Utilisateur, Timestamp> colCreatedAt = new TableColumn<>("Créé le");
            colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

            TableColumn<Utilisateur, Timestamp> colUpdatedAt = new TableColumn<>("Mis à jour le");
            colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

            // Création de la TableView et ajout des colonnes
             tableView = new TableView<>();
            tableView.getColumns().addAll(colId, colNom, colEmail, colCreatedAt, colUpdatedAt);

            // Chargement des données depuis la méthode getAllUtilisateurs()
            tableView.setItems(FXCollections.observableArrayList(gestionUtilisateurs.getAllUtilisateurs()));

            retour = new Button("Retour");
            retour.setOnAction(e -> stageListe.close());

            vbox = new VBox(10);
            vbox.setPadding(new Insets(15));
            vbox.getChildren().addAll(tableView, retour);

            Scene scene = new Scene(vbox, 650, 400);
            stageListe.setScene(scene);
            stageListe.show();
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
