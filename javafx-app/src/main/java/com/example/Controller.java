// package com.example;

// import javafx.fxml.FXML;
// import javafx.scene.control.TextField;

// public class Controller {

//     // Références aux champs de saisie dans le FXML
//     @FXML
//     private TextField nomField;

//     @FXML
//     private TextField prenomField;

//     @FXML
//     private TextField emailField;

//     // Méthode appelée lors du clic sur le bouton "Ajouter"
//     @FXML
//     private void handleAjouter() {
//         // Récupérer les valeurs des champs de saisie
//         String nom = nomField.getText();
//         String prenom = prenomField.getText();
//         String email = emailField.getText();

//         // Afficher les valeurs dans la console (ou les stocker dans des variables)
//         System.out.println("Nom: " + nom);
//         System.out.println("Prenom: " + prenom);
//         System.out.println("Email: " + email);

//         // Optionnel : Effacer les champs après l'ajout
//         nomField.clear();
//         prenomField.clear();
//         emailField.clear();
//     }
// }

package com.example;

// import javafx.fxml.FXML;
// import javafx.fxml.initialize;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur, Integer> colId;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, Timestamp> colCreated;
    @FXML
    private TableColumn<Utilisateur, Timestamp> colUpdated;

    private GestionUtilisateurs gestionUtilisateurs;

    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connexion connexion = new Connexion();
        connexion.Connection();
        gestionUtilisateurs = new GestionUtilisateurs(connexion);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        colUpdated.setCellValueFactory(new PropertyValueFactory<>("updated_at"));

        refreshTable();  // Chargement initial automatique
    }

    @FXML
    private void refreshTable() {
        tableView.getItems().clear();
        tableView.getItems().addAll(gestionUtilisateurs.getAllUtilisateurs());
    }
}
