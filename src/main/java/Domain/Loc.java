package Domain;

public class Loc extends Entitate{
    int rand;
    String loja;
    int numar;
    double pret;
    boolean stare;

    public Loc(int id, int rand, String loja, int numar, double pret, boolean stare) {
        super(id);
        this.rand = rand;
        this.loja = loja;
        this.numar = numar;
        this.pret = pret;
        this.stare = stare;
        //stare: 1-ocupat, 0-liber
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean getStare() {
        return stare;
    }

    public void setStare(boolean stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Loc{" +
                "rand=" + rand +
                ", loja='" + loja + '\'' +
                ", numar=" + numar +
                ", pret=" + pret +
                ", stare=" + stare +
                '}';
    }
}
