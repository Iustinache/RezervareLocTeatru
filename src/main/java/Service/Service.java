package Service;

import Domain.Spectator;
import Domain.Rezervare;
import Domain.Loc;

import Repository.IRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Service {
    protected IRepository<Spectator> repoSpec;
    protected IRepository<Rezervare> repoRez;
    protected IRepository<Loc> repoLoc;

    public Service(IRepository<Spectator> repo1, IRepository<Loc> repo2, IRepository<Rezervare> repo3) {
        repoSpec = repo1;
        repoLoc = repo2;
        repoRez = repo3;
    }

    private int makeRezervareId() {
        try {
            return repoRez.findAll().stream()
                    .mapToInt(Rezervare::getId)
                    .max().orElse(0) + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getSpectatorId() {
        return repoSpec.getSize() + 2;
    }

    public List<Loc> getSala() throws Exception {
        return repoLoc.findAll();
    }

    public void addSpectatorUi(String nume, String prenume, String email, String parola, String numarTelefon) throws Exception {
        int id_s;
        id_s = getSpectatorId();
        addSpectator(id_s, nume, prenume, email, parola, numarTelefon);
    }

    public void efectuareRezervare(String email, String parola, int[] lista) throws Exception {
        Optional<Spectator> existing = repoSpec.findAll().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst();

        int id_s;
        id_s = existing.get().getId();

        for (int i : lista) {
            efectuareRezervarePeLoc(id_s, i);
        }

    }

    public void efectuareRezervarePeLoc(int id_s, int id_l) throws Exception {
        LocalDate azi = LocalDate.now();
        Date data = Date.from(azi.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Loc loc = repoLoc.getEntityById(id_l);
        if (loc.getStare()) {
            throw new Exception();
        }

        Rezervare r = new Rezervare(makeRezervareId(), "Spectacol azi", data, id_s, id_l);
        repoRez.add(r);
        repoLoc.getEntityById(id_l).setStare(true);
    }

    private void resetareStareLocuriLaOra6() {
    }

    private void setareSpectacol() {
    }

    private void efectuareResetareZilnica() {
    }

    private boolean findSpectatorByEmail(String email) {
        List<Spectator> spectators = null;
        try {
            spectators = repoSpec.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Spectator s : spectators) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public Spectator getSpectatorByEmail(String email) {
        List<Spectator> spectators = null;
        try {
            spectators = repoSpec.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Spectator s : spectators) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s;
            }
        }
        return null;
    }

    public boolean verificareLogin(String email, String parola) {
        Spectator s = getSpectatorByEmail(email);
        if (!Objects.equals(parola, s.getParola())) {
            return false;
        }
        return true;
    }


//------CRUD-----------------


    public void addSpectator(int id_s, String nume, String prenume, String email, String parola, String numarTelefon) throws Exception {
        Spectator s = new Spectator(id_s, nume, prenume, email, parola, numarTelefon);
        //todo:validare daca e necesara
        repoSpec.add(s);
    }

    public void addRezervare(int id_r, String numeSpectacol, Date data, int id_s, int id_l) throws Exception {
        Rezervare r = new Rezervare(id_r, numeSpectacol, data, id_s, id_l);
        //todo:validare
        repoRez.add(r);
    }

    public Collection<Spectator> findAllSpectatori() throws Exception {
        return repoSpec.findAll();
    }

    public void updateSpectator(int id_s, String nume, String prenume, String email, String parola, String numarTelefon) throws Exception {
        repoSpec.update(repoSpec.getEntityById(id_s), new Spectator(id_s, nume, prenume, email, parola, numarTelefon));
    }

}
