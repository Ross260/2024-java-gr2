package Exo_etudiant;

public class Etudiant {
    private String nom;
    private String prenom;
    private String classe;

    // mon constructeur avec parametre
    public Etudiant(String nom, String prenom, String classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
    }

    public void AfficherListe() {
        
    }

    public String getNom () {
        return this.nom;
    }

    public String getPrenom () {
        return this.prenom;
    }

    public String getClasse () {
        return this.classe;
    }
}

