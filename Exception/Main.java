package Exception;

public class Main {

    static String[] cours = {"Dev java", "management", "Cyber"};

    public static String getElement(int index) {
        if (cours.length < index) {
            throw new ArrayIndexOutOfBoundsException("Vous chercher un élément qui est hors du tableau !");
        }

        return cours[index];
    }
    public static void main (String[] args) {
        try{
            System.out.println(getElement(6));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erreur capturée: " + e.getMessage());
        }
        
    }
}
