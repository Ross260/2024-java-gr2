package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer un label
        Label label = new Label("Bonjour, JavaFX !");
        
        // Créer la scène et appliquer le fichier CSS
        Scene scene = new Scene(label, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        
        // Configurer la fenêtre principale
        primaryStage.setTitle("Ma Première Application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}