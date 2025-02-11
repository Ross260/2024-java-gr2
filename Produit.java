public class Produit {
    protected double prix;

    // constructeur
    public Produit() {}

    //constructeur avec parametre
    public Produit(double prix) {
        this.prix = prix;
    }

    public void calculerPrix () {
        System.out.println("le prix calculé s'élève à " + this.prix);
    }
}



