package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Créer un label
        Label label = new Label("Bonjour, JavaFX !");
        
        // Créer la scène et appliquer le fichier CSS
        Scene scene = new Scene(label, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        VBox root = FXMLLoader.load(getClass().getResource("/layouts/App.fxml"));
        
        // Configurer la fenêtre principale
        primaryStage.setTitle("Ma Première Application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}