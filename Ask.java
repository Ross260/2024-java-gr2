import java.util.Scanner;

public class Ask {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrer votre nom");
        String nom = scanner.nextLine();


        System.out.println("Entrer votre age");
        int age = 0;
        if (scanner.hasNextInt()) {
            age = scanner.nextInt();
        }else{
            System.out.println("Age non correcte");
            System.exit(0);
        }

        scanner.close(); // Fermeture du scanner

        if (age > 18 ) {
            System.out.println("Votre nom est " + nom + " vous avez, " + age + "ans et vous etes majeur");
        }else{
            System.out.println("Votre nom est " + nom + "vous avez, " + age + "ans et vous etes mineur");

        }

         // ssh-keygen -t ed25519 -C "rossketcha@gmail.com" :: pour générer des clés de connexion sécuriser à github
        
        // protocole de cryptage :: ed25519
        // /c/Users/student/.ssh/id_ed25519.pub :: fichier où se trouve la clé public
            // ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAILxU80N2pjup7llVwdT6lPkpPYll+2IWLPYRwfQpbLHa rossketcha@gmail.com  :: la clé public
        // copier toute les clé avec le mail à la fin et le coller dans nex connexion SSH sur github

        

    }
}
