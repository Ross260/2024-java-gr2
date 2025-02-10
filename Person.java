public class Person {
    private String nom;
    private int age;

    // constructeur
    public Person() {}

    public Person(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    void sePresenter(){
        System.out.println("Je m'appele " + this.nom + " et j'ai " + this.age + " ans");
    }
}
