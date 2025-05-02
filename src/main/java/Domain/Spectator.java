package Domain;

public class Spectator extends Entitate {
    String nume;
    String prenume;
    String email;
    String numarTelefon;

    public Spectator(int id, String nume, String prenume, String email, String numarTelefon) {
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.numarTelefon = numarTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    @Override
    public String toString() {
        return "Spectator{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                '}';
    }
}
