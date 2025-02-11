public class Livre extends Produit{
     // constructeur
     public Livre() {}

     //constructeur avec parametre
     public Livre(double prix) {
         this.prix = prix;
     }
 
     @Override
     public void calculerPrix () {
         System.out.println("Le prix de livre est de " + this.prix*5/100);
     }
}

