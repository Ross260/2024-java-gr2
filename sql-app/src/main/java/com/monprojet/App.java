// package com.monprojet;

// import java.sql.Timestamp;
// // import java.sql.Connection;
// //import java.sql.DriverManager;
// // import java.sql.SQLException;
// // import java.sql.ResultSet;
// // import java.sql.Statement;
// // import java.sql.Timestamp;
// import java.util.Scanner;



// public class App 
// {
//     public static void main( String[] args )
//     {
//         int choix= 0;
//         do{

//             Scanner scanner = new Scanner(System.in);
//             System.out.println("Choisissez une option :");
//             System.out.println("1- Ajouter un utilisateur dans la table");
//             System.out.println("2- Lister les utilisateur de la table");
//             System.out.println("3- Supprimer un utilisateur par son id");
//             System.out.println("4- Edit d'un utilisateur par son id");
//             System.out.println("5- rechercher un utilisateur par son mail et son nom");
//             System.out.print("Votre choix :");
            
//             choix = scanner.nextInt();

//             switch (choix) {
//                 case 1:
//                     // Création d'une instance de la classe de gestion
//                     GestionUtilisateur gestion = new GestionUtilisateur();

//                     // Demande des informations à l'utilisateur
//                     System.out.println("\n Ajout d'un nouvel utilisateur ");

//                     // System.out.print(" Entrez l'ID : ");
//                     // int id = scanner.nextInt();
                    
//                     System.out.print(" Entrez le nom : ");
//                     String nom = scanner.nextLine();
//                     scanner.nextLine(); // Consommer la ligne restante

//                     System.out.print(" Entrez l'email : ");
//                     String email = scanner.nextLine();

//                     // Dates actuelles pour created_at et update_at
//                     Timestamp createdAt = new Timestamp(System.currentTimeMillis());
//                     Timestamp updatedAt = new Timestamp(System.currentTimeMillis());


//                     // Création de l'objet Utilisateur
//                     Utilisateur nouvelUtilisateur = new Utilisateur(nom, email, createdAt, updatedAt);

//                     // Ajout à la base de données
//                     gestion.addUtilisateurs(nouvelUtilisateur);

//                     // Fermeture du scanner
//                     scanner.close();
//                     break;
//                 case 2:
//                     GestionUtilisateur ges = new GestionUtilisateur();
//                     ges.listUtilisateurs();
//                     break;
//                 case 3:
                    
//                     break;
//                 case 4:
                    
//                     break;
            
//                 default:
//                     break;
//             }
//         } while(choix != 0);   
//     }
// }

package com.monprojet;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connexion connexion = new Connexion();
        try {
            connexion.Connection();
            GestionUtilisateurs gestionUtilisateurs = new GestionUtilisateurs(connexion);
            int choix;

            do {
                System.out.println("\nMenu:");
                System.out.println("1: Insérer un utilisateur");
                System.out.println("2: Supprimer un utilisateur");
                System.out.println("3: Modifier un utilisateur");
                System.out.println("4: Afficher tous les utilisateurs");
                System.out.println("5: Rechercher un utilisateur par nom ou email");
                System.out.println("6: Sauver tous les utilisateurs");
                System.out.println("7: Quitter");
                System.out.print("Choisissez une option : ");
                choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne

                switch (choix) {
                    case 1:
                        System.out.print("Entrez le nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez l'email : ");
                        String email = scanner.nextLine();
                        
                        gestionUtilisateurs.insererUtilisateur(nom, email);
                        break;
                    case 2:
                        System.out.print("Entrez l'ID de l'utilisateur à supprimer : ");
                        int idSupprimer = scanner.nextInt();
                        gestionUtilisateurs.supprimerUtilisateur(idSupprimer);
                        break;
                    case 3:
                        System.out.print("Entrez l'ID de l'utilisateur à modifier : ");
                        int idModifier = scanner.nextInt();
                        scanner.nextLine(); // Pour consommer la nouvelle ligne
                        System.out.print("Entrez le nouveau nom : ");
                        String nouveauNom = scanner.nextLine();
                        System.out.print("Entrez le nouvel email : ");
                        String nouvelEmail = scanner.nextLine();
                        gestionUtilisateurs.modifierUtilisateur(idModifier, nouveauNom, nouvelEmail);
                        break;
                    case 4:
                        gestionUtilisateurs.afficherUtilisateurs();
                        break;
                    case 5:
                        System.out.print("Entrez un mot-clé pour rechercher un utilisateur : ");
                        String motCle = scanner.nextLine();
                        gestionUtilisateurs.rechercherUtilisateur(motCle);
                        break;
                    case 6:
                        // gestionUtilisateurs.toCSV();
                        break;
                    case 7:
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } while (choix != 7);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}