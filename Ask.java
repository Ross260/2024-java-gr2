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

    }
}
