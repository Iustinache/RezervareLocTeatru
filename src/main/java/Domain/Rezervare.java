package Domain;

import java.util.Date;

public class Rezervare extends Entitate {
    String numeSpectacol;
    Date data;
    int spectatorId;
    int locId;


    public Rezervare(int id, String numeSpectacol, Date data, int spectatorID, int locId) {
        super(id);
        this.numeSpectacol = numeSpectacol;
        this.data = data;
        this.spectatorId = spectatorID;
        this.locId = locId;
    }

    public String getNumeSpectacol() {
        return numeSpectacol;
    }

    public void setNumeSpectacol(String numeSpectacol) {
        this.numeSpectacol = numeSpectacol;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSpectatorID() {
        return spectatorId;
    }

    public void setSpectatorID(int spectatorID) {
        this.spectatorId = spectatorID;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeSpectacol='" + numeSpectacol + '\'' +
                ", data=" + data +
                ", spectator=" + spectatorId +
                ", loc=" + locId +
                ", id=" + id +
                '}';
    }
}
