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