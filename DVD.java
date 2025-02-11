public class DVD extends Produit{
    // constructeur
    public DVD() {}

    //constructeur avec parametre
    public DVD(double prix) {
        this.prix = prix;
    }

    @Override
    public void calculerPrix () {
        System.out.println("Le prix de livre est de " + this.prix*20/100);
    }
}