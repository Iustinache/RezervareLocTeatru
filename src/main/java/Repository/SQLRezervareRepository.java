package Repository;

import Domain.Loc;
import Domain.Rezervare;
import Domain.Spectator;

import Domain.Spectator;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SQLRezervareRepository extends RepoMemory<Rezervare> {
    Connection connection;
    //change URL here to relative path or your (absolute) path
    String db_url = "jdbc:sqlite:teatru.db";

    public SQLRezervareRepository() {
        openConnection();
        createTable();
        //initRezervariTable();
        loadData();
    }

    private void loadData() {
        entities.addAll(this.findAll());
    }


    private void initRezervariTable() {
        List<Rezervare> rezervariList = new ArrayList<>();
        Spectator s1 = new Spectator(1,"Nume1","Prenume1","test1@gmail.com","+4070000000000");
        Spectator s2 = new Spectator(2,"Nume2","Prenume2","test2@gmail.com","+4070000000001");
        Spectator s3 = new Spectator(3,"Nume3","Prenume3","test3@gmail.com","+4070000000002");
        Spectator s4 = new Spectator(4,"Nume4","Prenume4","test4@gmail.com","+4070000000003");
        Spectator s5 = new Spectator(5,"Nume5","Prenume5","test5@gmail.com","+4070000000004");

        Loc loc1 = new Loc(1, 1, "A", 1, 50.0, false); // liber
        Loc loc2 = new Loc(2, 1, "A", 2, 50.0, true);  // ocupat
        Loc loc3 = new Loc(3, 2, "B", 1, 60.0, false); // liber
        Loc loc4 = new Loc(4, 2, "B", 2, 60.0, true);  // ocupat
        Loc loc5 = new Loc(5, 3, "C", 1, 70.0, false); // liber


        rezervariList.add(new Rezervare(100,"Nume1",new Date(2025,04,22),1, 1));
        rezervariList.add(new Rezervare(101,"Nume2",new Date(2025,04,22),2,2));
        rezervariList.add(new Rezervare(102,"Nume3",new Date(2025,04,22),3,3));
        rezervariList.add(new Rezervare(103,"Nume4",new Date(2025,04,22),4,4));
        rezervariList.add(new Rezervare(104,"Nume5",new Date(2025,04,22),5,5));

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO rezervari VALUES (?,?,?,?,?);")) {
            for (Rezervare r : rezervariList) {
                statement.setInt(1, r.getId());
                statement.setString(2, r.getNumeSpectacol());
                statement.setDate(3, new java.sql.Date(r.getData().getTime()));
                statement.setInt(4, r.getSpectatorID());
                statement.setInt(5, r.getLocId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(db_url);
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            System.out.println("eroare la crearea conexiunii" + e);
        }
    }

    private void createTable() {
        String s = "Create Table if not exists rezervari( id_r int, numeSpectacol varchar(30), data date, id_s int , id_l int,  PRIMARY KEY (id_s), FOREIGN KEY (id_s) REFERENCES spectatori(id_s), FOREIGN KEY (id_l) REFERENCES locuri(id_l))";
        try {

            Statement statement = connection.createStatement();
            boolean execution_result = statement.execute(s);
            System.out.println("Execution result from createTable()" + execution_result);
        } catch (SQLException e) {
            System.out.println("eroare la crearea tabelei spectatori" + e);
        }
    }

    @Override
    public void removeById(int id) {
        super.removeById(id);
        String s = "DELETE FROM rezervari WHERE id_r=?";
        //try-with-resources
        //https://docs.oracle.com/javase/8/docs/technotes/guides/language/try-with-resources.html
        try (PreparedStatement remove_statement = connection.prepareStatement(s)) {
            remove_statement.setInt(1, id);
            remove_statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Rezervare r) throws Exception {
        super.add(r);
        String s = "INSERT INTO rezervari VALUES (?,?,?,?,?);";
        try {
            PreparedStatement add_statement = connection.prepareStatement(s);

            add_statement.setInt(1, r.getId());
            add_statement.setString(2, r.getNumeSpectacol());
            add_statement.setDate(3, (java.sql.Date) r.getData());
            add_statement.setInt(4, r.getSpectatorID());
            add_statement.setInt(5, r.getLocId());

            add_statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }


    @Override
    public List<Rezervare> findAll() {
        List<Rezervare> resultList = new ArrayList<>();
        String s = "SELECT * FROM rezervari";
        try (PreparedStatement getAllSstatement = connection.prepareStatement(s)) {
            ResultSet result = getAllSstatement.executeQuery();
            while (result.next()) {
                Rezervare r = new Rezervare(result.getInt("id_r"), result.getString("numeSpectacol"),
                        result.getDate("data"), result.getInt("id_s"), result.getInt("id_l"));
                resultList.add(r);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}