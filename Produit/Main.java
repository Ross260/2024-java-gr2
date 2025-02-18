package Produit;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter ecrivain = new FileWriter("data.txt");
            ecrivain.write("Bonjour, monde !");
            ecrivain.close();
            System.out.println("Écriture terminée.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier.");
        }
    }
}