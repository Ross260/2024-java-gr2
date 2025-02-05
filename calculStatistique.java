import java.util.Arrays;

public class CalculStatistique {
    public static void main() {
        int[] Tableau = {2, 3, 4, 5, 6, 7, 8, 9, 11, 34, 23, 16, 12, 14};
        double Moyenne = 0;
        double somme = 0;

        for (int x : Tableau) {
            somme = x + somme;
        }

        Moyenne = somme/Tableau.length;

        System.out.println("La moyenne est " + Moyenne);

        /*
         * CALCUL DE LA MEDIANE (le chiffre du milieu) & L'ECART TYPE
         * 
         * -> trier le tableau
         * -> Faire le calcul de la médiane
         * ->faire le calcul de l'écart type
        */

       // Tri et médiane
       Arrays.sort(Tableau);
       double median = 0 ;

       if (Tableau.length % 2 == 0) {
           median = (Tableau[Tableau.length / 2] + Tableau[Tableau.length / 2 - 1]) / 2.0;
       } else {
           median = Tableau[Tableau.length / 2];
       }
       System.out.println("Médiane: " + median);
       
       // Écart-type
       double variance = 0;
       for (int value : Tableau) {
           variance += Math.pow(value - Moyenne , 2);
       }
       double stdDev = Math.sqrt(variance / Tableau.length);
       System.out.println("Écart-type: " + stdDev);
       
       // Min et Max
       System.out.println("Min: " + Tableau[0]);
       System.out.println("Max: " + Tableau[Tableau.length - 1]);
   }
}
