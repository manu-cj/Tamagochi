package tamagochi.model;

public class Etat {
    private int age;
    private int sante;
    private int faim;
    private int bonheur;

    public Etat(int age, int sante, int faim, int bonheur) {
        this.age = age;
        this.sante = sante;
        this.faim = faim;
        this.bonheur = bonheur;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSante() {
        return sante;
    }

    public void setSante(int sante) {
        this.sante = sante;
    }

    public int getFaim() {
        return faim;
    }

    public void setFaim(int faim) {
        this.faim = faim;
    }

    public int getBonheur() {
        return bonheur;
    }

    public void setBonheur(int bonheur) {
        this.bonheur = bonheur;
    }
    
}
