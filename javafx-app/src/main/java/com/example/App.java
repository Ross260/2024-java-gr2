package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Timestamp;
import java.util.List;



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
        Button btnTocsv = new Button("Convertir en CSV");

        btnListe.setMaxWidth(200);
        btnAjouter.setMaxWidth(200);
        btnSupprimer.setMaxWidth(200);
        btnModifier.setMaxWidth(200);
        btnRechercher.setMaxWidth(200);
        btnTocsv.setMaxWidth(200);

        // Actions des boutons
        btnListe.setOnAction(e -> ouvrirFenetre("Liste des utilisateurs"));
        btnAjouter.setOnAction(e -> ouvrirFenetre("Ajouter utilisateur"));
        btnSupprimer.setOnAction(e -> ouvrirFenetre("Supprimer utilisateur"));
        btnModifier.setOnAction(e -> ouvrirFenetre("Modifier utilisateur"));
        btnRechercher.setOnAction(e -> ouvrirFenetre("Rechercher utilisateur"));
        btnTocsv.setOnAction(e -> ouvrirFenetre("Convertir en CSV"));

        root.getChildren().addAll(btnListe, btnAjouter, btnSupprimer, btnModifier, btnRechercher, btnTocsv);

        Scene scene = new Scene(root, 600, 500);
        // Charger le fichier CSS
        String css = getClass().getResource("/styles/main.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des utilisateurs");
        primaryStage.show();
    }

    // Fonction pour ouvrir des fenêtres specifiques
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

                // Appel à la methode d'ajout en base de donnees
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

            // Creation TableView
            TableView<Utilisateur> tableView = new TableView<>();

            TableColumn<Utilisateur, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<Utilisateur, String> colEmail = new TableColumn<>("Email");
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Utilisateur, Timestamp> colCreatedAt = new TableColumn<>("Cre le");
            colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

            TableColumn<Utilisateur, Timestamp> colUpdatedAt = new TableColumn<>("Mis à jour le");
            colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

            // Creation de la TableView et ajout des colonnes
             tableView = new TableView<>();
            tableView.getColumns().addAll(colId, colNom, colEmail, colCreatedAt, colUpdatedAt);

            // Chargement des donnees depuis la methode getAllUtilisateurs()
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
        else if (titre.equals("Supprimer utilisateur")) {
            Stage stageSuppression = new Stage();
            stageSuppression.setTitle("Supprimer un utilisateur");
        
            vbox = new VBox(10);
            vbox.setPadding(new Insets(20));
        
            Label lblId = new Label("ID utilisateur à supprimer :");
            TextField fieldId = new TextField();
            fieldId.setPromptText("Entrez l'ID");
        
            Button btnSupprimer = new Button("Confirmer suppression");
            btnSupprimer.setOnAction(e -> {
                try {
                    int id = Integer.parseInt(fieldId.getText());
                    gestionUtilisateurs.supprimerUtilisateur(id);
                    fieldId.clear();
                } catch (NumberFormatException ex) {
                    System.err.println("Veuillez entrer un ID valide (nombre entier).");
                }
            });
        
            retour = new Button("Retour");
            retour.setOnAction(e -> stageSuppression.close());
        
            vbox.getChildren().addAll(lblId, fieldId, btnSupprimer, retour);
        
            Scene scene = new Scene(vbox, 350, 200);

            stageSuppression.setScene(scene);
            stageSuppression.show();
        }
        
        else if (titre.equals("Modifier utilisateur")) {
            Stage stageModification = new Stage();
           
            stageModification.setTitle("Modifier un utilisateur");
        
            vbox = new VBox(10);
            vbox.setPadding(new Insets(20));
        
            Label lblId = new Label("ID utilisateur :");
            TextField fieldId = new TextField();
            fieldId.setPromptText("Entrez l'ID");
        
            Label lblNom = new Label("Nom :");
            TextField fieldNom = new TextField();
            fieldNom.setPromptText("Entrez le nouveau nom");
        
            Label lblEmail = new Label("Email :");
            TextField fieldEmail = new TextField();
            fieldEmail.setPromptText("Entrez le nouvel email");
        
            Button btnConfirmer = new Button("Confirmer modification");
            btnConfirmer.setOnAction(e -> {
                int id = Integer.parseInt(fieldId.getText());
                String nom = fieldNom.getText();
                String email = fieldEmail.getText();
        
                // Appel à la methode de modification en base de donnees
                gestionUtilisateurs.modifierUtilisateur(id, nom, email);
        
                fieldId.clear();
                fieldNom.clear();
                fieldEmail.clear();
            });
        
            retour = new Button("Retour");
            retour.setOnAction(e -> stage.close());
        
            vbox.getChildren().addAll(
                lblNom, fieldNom,
                lblEmail, fieldEmail,
                lblId, fieldId,
                btnConfirmer, retour
            );}  
            else if (titre.equals("Rechercher utilisateur")) {
                Stage stageRecherche = new Stage();
                stageRecherche.setTitle("Rechercher un utilisateur");

                vbox = new VBox(10);
                vbox.setPadding(new Insets(20));

                // Label et champ pour la recherche
                Label lblCritere = new Label("Rechercher un utilisateur par :");
                ComboBox<String> critereBox = new ComboBox<>();
                critereBox.getItems().addAll("Email", "Nom");
                critereBox.setValue("Email"); // Valeur par défaut

                Label lblRecherche = new Label("Valeur de recherche :");
                TextField fieldRecherche = new TextField();
                fieldRecherche.setPromptText("Entrez l'email ou le nom");

                TextArea resultArea = new TextArea();
                resultArea.setEditable(false);
                resultArea.setPromptText("Résultat de la recherche...");

                // Bouton de recherche
                Button btnRechercher = new Button("Rechercher");
                btnRechercher.setOnAction(e -> {
                    resultArea.clear();
                    String valeurRecherche = fieldRecherche.getText();

                    // Vérification du critère sélectionné
                    if (valeurRecherche.isEmpty()) {
                        resultArea.setText("Veuillez entrer une valeur pour rechercher.");
                        return;
                    }

                    try {
                        List<Utilisateur> utilisateurs;
                        if (critereBox.getValue().equals("Email")) {
                            utilisateurs = gestionUtilisateurs.searchUtilisateurByEmail(valeurRecherche);
                        } else {
                            utilisateurs = (List<Utilisateur>) gestionUtilisateurs.searchUtilisateurByName(valeurRecherche);
                        }

                        if (!utilisateurs.isEmpty()) {
                            for (Utilisateur user : utilisateurs) {
                                resultArea.appendText("ID : " + user.getId() + 
                                    ", Nom : " + user.getNom() + 
                                    ", Email : " + user.getEmail() + "\n");
                            }
                        } else {
                            resultArea.setText("Aucun utilisateur trouvé.");
                        }
                    } catch (Exception ex) {
                        resultArea.setText("Erreur lors de la recherche : " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });

                // Bouton retour
                retour = new Button("Retour");
                retour.setOnAction(e -> stageRecherche.close());

                vbox.getChildren().addAll(lblCritere, critereBox, lblRecherche, fieldRecherche, btnRechercher, resultArea, retour);

                Scene scene = new Scene(vbox, 600, 400);
                stageRecherche.setScene(scene);
                stageRecherche.show();
            }
            else if (titre.equals("Convertir en CSV")) {
                Stage stageCsv = new Stage();
                stageCsv.setTitle("Exporter les utilisateurs en CSV");

                vbox = new VBox(10);
                vbox.setPadding(new Insets(20));

                Label lblInfo = new Label("Cliquez sur le bouton ci-dessous pour exporter les utilisateurs en CSV.");

                Button btnExporter = new Button("Exporter en CSV");
                btnExporter.setOnAction(e -> {
                    List<Utilisateur> utilisateurs = gestionUtilisateurs.getAllUtilisateurs(); // Récupération de la liste des utilisateurs
                    String cheminFichier = "utilisateurs.csv"; // Nom du fichier de sortie

                    // Appel de la classe de conversion
                    ConvertToCsv.convertirListeUtilisateursEnCsv(utilisateurs, cheminFichier);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exportation terminée");
                    alert.setHeaderText(null);
                    alert.setContentText("Fichier CSV généré avec succès : " + cheminFichier);
                    alert.showAndWait();
                });

                retour = new Button("Retour");
                retour.setOnAction(e -> stageCsv.close());

                vbox.getChildren().addAll(lblInfo, btnExporter, retour);

                Scene scene = new Scene(vbox, 400, 200);
                stageCsv.setScene(scene);
                stageCsv.show();
            }

                  
            else {
            Label label = new Label("Fenêtre : " + titre + " (en attente d'implementation)");
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
