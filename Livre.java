public class Produit {
    protected double prix;

    public void calculerPrix () {
        System.out.println("le prix calculé s'élève à " + this.prix);
    }
}

public class Livre extends Produit {
    @Override
    public void calculerPrix () {
        System.out.println("Le prix de livre est de " + this.prix*5/100);
    }
}

public class DVD extends Produit {
    @Override
    public void calculerPrix () {
        System.out.println("Le prix de DVD est de " + this.prix*20/100);
    }
}