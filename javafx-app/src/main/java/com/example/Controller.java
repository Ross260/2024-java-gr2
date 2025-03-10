package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label monLabel; // Référence au label dans le FXML

    // Méthode appelée lors du clic sur le bouton
    @FXML
    private void changerTexte() {
        monLabel.setText("Vous avez cliqué sur le bouton !");
    }
}