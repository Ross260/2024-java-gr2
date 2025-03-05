package com.monprojet;

import java.sql.Timestamp;
import java.time.LocalDateTime;
// import java.sql.Connection;
//import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.sql.ResultSet;
// import java.sql.Statement;
// import java.sql.Timestamp;
import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        int choix= 0;
        do{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez une option :");
            System.out.println("1- Ajouter un utilisateur dans la table");
            System.out.println("2- Lister les utilisateur de la table");
            System.out.println("3- Supprimer un utilisateur par son id");
            System.out.println("4- Edit d'un utilisateur par son id");
            System.out.println("5- rechercher un utilisateur par son mail et son nom");
            System.out.print("Votre choix :");
            
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    // Création d'une instance de la classe de gestion
                    GestionUtilisateur gestion = new GestionUtilisateur();

                    // Demande des informations à l'utilisateur
                    System.out.println("🔹 Ajout d'un nouvel utilisateur 🔹");

                    System.out.print("👉 Entrez l'ID : ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consommer la ligne restante

                    System.out.print("👉 Entrez le nom : ");
                    String nom = scanner.nextLine();

                    System.out.print("👉 Entrez l'email : ");
                    String email = scanner.nextLine();

                    // Dates actuelles pour created_at et update_at
                    Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                    Timestamp updatedAt = new Timestamp(System.currentTimeMillis());


                    // Création de l'objet Utilisateur
                    Utilisateur nouvelUtilisateur = new Utilisateur(nom, email, createdAt, updatedAt);

                    // Ajout à la base de données
                    gestion.addUtilisateurs(nouvelUtilisateur);

                    // Fermeture du scanner
                    scanner.close();
                    break;
                case 2:
                    GestionUtilisateur ges = new GestionUtilisateur();
                    ges.listUtilisateurs();
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
            
                default:
                    break;
            }
        } while(choix != 0);   
    }
}


// package com.monprojet;

// import java.util.Scanner;

// public class App 
// {
//     public static void main( String[] args )
//     {
//         /* On clear la console */
//         System.out.print("\033[H\033[2J");   
//         System.out.flush();

//         System.out.println( "Hello World!" );
//         Connexion link = new Connexion();
//         GestionUtilisateur gu = new GestionUtilisateur(link);

//         /* On demande à l'utilisateur ce qu'il veut faire */
//         Scanner sc = new Scanner(System.in);
//         int choice = 0;

//         do { 
//             System.out.println("Que voulez vosu faire ?");
//             System.out.println("1 - Lister les utilisateurs");
//             System.out.println("2 - Ajouter un utilisateur");
//             System.out.println("0 - Quitter");
//             choice = sc.nextInt();
            
//             System.out.print("\033[H\033[2J");   
//             System.out.flush(); 
            
//             switch (choice) {
//                 case 1:
//                     gu.listUtilisateurs();
//                     System.out.println("---------------------");
//                     break;

//                 case 2:
//                     System.out.print("Nom de l'utilisateur: ");
//                     sc.nextLine();
//                     String nom = sc.nextLine();

//                     System.out.print("Email de l'utilisateur: ");
//                     String email = sc.nextLine();

//                     Utilisateur utilisateur = new Utilisateur(nom, email);

//                     gu.addUtilisateurs(utilisateur);
//                     System.out.println("---------------------");
//                     break;
            
//                 default:
//                     System.out.println("Pas d'action pour ce choix !");
//                     break;
//             }
//         } while(choice != 0);

//         link.close();
//         sc.close();
//     }
// }