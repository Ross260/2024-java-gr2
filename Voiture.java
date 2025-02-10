public class Voiture {
    private String marque;
    private String modele;
    private String couleur;
    

    String couleur[] = colorAutorized

    //constructeur 

    public Voiture(String marque, String modele, String couleur){
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
    }

    //methode 
    public void demarrer() {
        System.out.println("ma voiture démarre");
    }

    public void accelerer() {
        System.out.println("ma voiture accelere");
    }

    public void freiner() {
        System.out.println("ma voiture freine");
    }


    // les getters
    public String getMarque() {
        return this.marque;
    }

    public String getModele() {
        return this.modele;
    }

    public String getCouleur() {
        return this.couleur;
    }

}