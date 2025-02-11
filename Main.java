
  
import java.util.Scanner;

public class Main {
    public static void main() {
       
        /*
        // Crée un objet Scanner qui lit depuis l'entrée standard (clavier)
        Scanner scanner = new Scanner(System.in);

        // Demande à l'utilisateur de saisir une ligne de texte
        System.out.println("Veuillez saisir une couleur :");
        String couleur = scanner.nextLine();
        System.out.println("Vous avez saisi : " + couleur);

        boolean found = false;
        for(String color : Voiture.colorAutorized) {
            if (color.equals(couleur)) {
                found = true;
                break;
            }
        }

        if (found) {
            //Création de la voiture
            Voiture voiture = new Voiture("Toyota", "V9",couleur);

            //Affichage des infos de la voiture 
            System.out.println(voiture.getMarque() + " " + voiture.getModele() + " " + voiture.getCouleur());
        } else {
            System.out.println("Pas de couleur");
        } */
        
        // //Déclaration et initialisation de l'objet
        // Voiture voiture = new Voiture("Toyota", "V9","bleu");
        // System.out.println(voiture.getMarque() + " " + voiture.getModele() + " " + voiture.getCouleur());
        // // Utilisation des méthodes de l'objet
        // voiture.demarrer();
        // voiture.accelerer();
        


        //PERSONNE

        // Person P1 = new Person("Ross", 12);
        
        // P1.sePresenter();


        
        //COMPTE BANCAIRE

        // CompteBancaire compte1 = new CompteBancaire("Loic", 1000);
        // compte1.retirer(500);
        // compte1.deposer(300);
        // compte1.retirer(3000);


        
        // ANIMAL



        //PRODUIT(Livre & DVD)
        Produit produit1 = new Livre(500);
        Produit produit2 = new DVD(500);
        produit1.calculerPrix();
        produit2.calculerPrix();

        

    }
}
