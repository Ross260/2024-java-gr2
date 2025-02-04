public class calculStatistique {
    public static void main() {
        int[] Tableau = {2, 3, 4, 5, 6, 7, 8, 9, 11, 34, 23, 16, 12, 14};
        double Moyenne = 0;
        double somme = 0;

        for (int x : Tableau) {
            somme = x + somme;
        }

        Moyenne = somme/Tableau.length;

        System.out.println("La moyenne est " + Moyenne);
    }
}
