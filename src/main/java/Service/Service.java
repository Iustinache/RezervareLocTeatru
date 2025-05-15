package Service;

import Domain.Spectator;
import Domain.Rezervare;
import Domain.Loc;

import Repository.IRepository;
import Repository.SQLLocRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
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
        return repoSpec.getSize() + 1;
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
            throw new Exception("Locul este deja rezervat.");
        }

        Rezervare r = new Rezervare(makeRezervareId(), "Spectacol azi", data, id_s, id_l);
        repoRez.add(r);

        // Creez un nou obiect Loc cu stare true
        Loc updatedLoc = new Loc(loc.getId(), loc.getRand(), loc.getLoja(), loc.getNumar(), loc.getPret(), true);

        // Fac update-ul in baza de date si in lista entities
        repoLoc.update(loc, updatedLoc);
    }


    public List<Integer> anuleazaToateRezervarile(String email, String parola) throws Exception {
        if (!verificareLogin(email, parola)) {
            throw new Exception("Date de autentificare incorecte!");
        }

        Spectator spectator = repoSpec.findAll().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new Exception("Spectatorul nu exista!"));

        int spectatorId = spectator.getId();

        List<Rezervare> rezervariSpectator = repoRez.findAll().stream()
                .filter(r -> r.getSpectatorID() == spectatorId)
                .toList();

        List<Integer> locuriAnulate = new ArrayList<>();

        for (Rezervare rezervare : rezervariSpectator) {
            int locId = rezervare.getLocId();
            locuriAnulate.add(locId);

            repoRez.removeById(rezervare.getId());

            Loc loc = repoLoc.getEntityById(locId);
            Loc updatedLoc = new Loc(loc.getId(), loc.getRand(), loc.getLoja(), loc.getNumar(), loc.getPret(), false);
            repoLoc.update(loc, updatedLoc);
        }

        return locuriAnulate;
    }


    public boolean verificareLogin(String email, String parola) throws Exception {
        Optional<Spectator> s = repoSpec.findAll().stream()
                .filter(spec -> spec.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (s.isEmpty()) {
            throw new Exception("Cont inexistent! Trebuie sa va creati cont. Sau verificati daca ati scris corect emailul si parola");
        }

        return s.get().getParola().equals(parola);
    }

    public double calculeazaCost(int[] locuriArray) throws Exception {
        double total = 0;
        for (int idLoc : locuriArray) {
            Loc loc = repoLoc.getEntityById(idLoc);
            total += loc.getPret();
        }
        return total;
    }


    //---------partea de reset  a orei ---------------

    private final String FILE_RESET = "reset_date.txt";

    private LocalDate readLastResetDate() {
        try {
            File file = new File(FILE_RESET);
            if (!file.exists()) {
                return null;
            }
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeLastResetDate(LocalDate date) {
        try (FileWriter writer = new FileWriter(FILE_RESET)) {
            writer.write(date.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetLocuriOdataPeZi() throws Exception {
        LocalDate azi = LocalDate.now();
        LocalDate ultimaReset = readLastResetDate();

        if (ultimaReset == null || !ultimaReset.equals(azi)) {
            resetLocuri(); // faci resetarea stării locurilor
            writeLastResetDate(azi); // actualizezi fișierul cu data de azi
        }
    }


    public void resetLocuri() throws Exception {
        ((SQLLocRepository) repoLoc).resetStareLocuri();
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
