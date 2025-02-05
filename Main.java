import java.util.Scanner;

public class Main {
    public static void main() {
        
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        String nom ;

        System.out.println("Votre choix: 1:Voiture, 2:Prenom");
        choix = scanner.nextInt();

        switch (choix) {
            case 1:
                //Déclaration et initialisation de l'objet
                Voiture V1 = new Voiture("Toyota", "V9","bleu");
                System.out.println(V1.getMarque() + " " + V1.getModele() + " " + V1.getCouleur());

                // Utilisation des méthodes de l'objet
                V1.demarrer();
                V1.accelerer();
                break;

            case 2:
                System.out.println("Quel est votre prénom ?");
                nom = scanner.nextLine();
        
                // Person
        
                Person P1 = new Person(nom, 12);
        
                P1.sePresenter();
        
                scanner.close(); // Fermeture du scanner
                break;
           
            default:
                // Instructions si variable ne correspond à aucun cas
                break;
        }
        

        

    }
}
