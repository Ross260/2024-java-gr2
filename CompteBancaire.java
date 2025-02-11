public class CompteBancaire {
    private String titulaire;
    private double solde;

    public CompteBancaire(String titulaire,double montant){
        this.titulaire = titulaire;
        this.solde = montant;
    }

    public void deposer(double montant) {
        if (montant > 0){
            this.solde += montant;
            System.out.println(this.titulaire + ": " + montant + " a été déposeé sur le compte.");
        }
    }

    public void retirer(double montant) {
        if (this.solde - montant > 0){
            this.solde -= montant;
            System.out.println(montant + " a été retiré du compte.");
        }else{
            System.out.println(this.titulaire + " Vous n'avez plus que " + this.solde + " sur votre compte");
        }
    }    

}

