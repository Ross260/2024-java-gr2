public class Animal {
    public void FaireDuBruit(){
        System.out.println("je fais du Bruit");
    }
}

public class Chat extends Animal {
    @Override
    public void FaireDuBruit () {
        System.out.println("Je miole");
    }
}

public class Chien extends Animal {
    @Override
    public void FaireDuBruit () {
        System.out.println("Le chien aboie");
    }
}
