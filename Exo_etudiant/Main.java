package Exo_etudiant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int Menu() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        System.out.println("\nMenu:\n");
        System.out.println("1. Voir la liste \n");
        System.out.println("2. Ajouter un étudiant \n");
        System.out.println("3. Supprimer un étudiant \n");
        System.out.println("0. Quitter\n\n");
        System.out.println("Entrez votre choix: ");
    
        choix = scanner.nextInt();
        
        return choix;

    }
    public static void main() {
        // Créer une liste d'étudiants
        List<Etudiant> etudiants = new ArrayList<>();

        // Ajouter des étudiants à la liste
        etudiants.add(new Etudiant("Dupont", "Alice", "1A"));
        etudiants.add(new Etudiant("Martin", "Bob", "2B"));
        etudiants.add(new Etudiant("Durand", "Charlie", "3C"));


        Scanner scanner = new Scanner(System.in);
            int choix;
            String nom;
            String prenom;
            String classe;
        
            do {
                choix = Menu();
                
        
                switch (choix) {
                    case 1:
                        System.out.println("Liste des étudiants \n");

                        // Afficher la liste des étudiants
                        for (Etudiant etudiant : etudiants) {
                            System.out.println(etudiant.getNom() + " " + etudiant.getPrenom() + " " + etudiant.getClasse());
                        }

                        break;
                    case 2:
                        System.out.println("Veiller entrer les différentes informations de l'étudiant:\n");
                        System.out.println("Entrez le nom de l'étudiant : ");
                        nom = scanner.nextLine();
                        System.out.println("Entrez le prenom de l'étudiant : ");
                        prenom = scanner.nextLine();
                        System.out.println("Entrez la classe de l'étudiant : ");
                        classe = scanner.nextLine();
                        etudiants.add(new Etudiant(nom, prenom, classe));

                        System.out.println("Etudiant enregistrer avec succes \n");

                        break;
                    case 3:
                        System.out.println("Information de l'étudiant à supprimer\n");
                        System.out.println("Nom de l'étudiant à supprimer");
                        nom = scanner.nextLine();

                        /* 
                         *-> bug sur la solution juste en bas
                         *-> trouver une methode de suppression
                        */

                        // int position = 0, i = 0;
                        // for (Etudiant etudiant : etudiants) {
                        //     i = i + 1;
                        //     if (etudiant.equals(nom)) {
                        //         position = i;
                        //         break;
                        //     }    
                        // }
                        // etudiants.remove(position);

                        //Solution
                        Iterator<Etudiant> iterator = etudiants.iterator();
                        while (iterator.hasNext()) {
                            Etudiant etudiant = iterator.next();
                            if (etudiant.getNom().equalsIgnoreCase(nom)) {
                                iterator.remove();
                                System.out.println("L'étudiant " + nom + " a été supprimé de la liste.");
                                break;
                            }
                        }

                        break;
                    case 0:
                        System.out.println("Au revoir!\n");
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez saisir un choix correct.\n");
                        break;
                }
            } while (choix != 0);
    
            scanner.close();
        
    }
}


