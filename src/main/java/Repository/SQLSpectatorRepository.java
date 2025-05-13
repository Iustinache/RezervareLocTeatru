package Repository;

import Domain.Spectator;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLSpectatorRepository extends RepoMemory<Spectator> {
    Connection connection;
    //change URL here to relative path or your (absolute) path
    String db_url = "jdbc:sqlite:teatru.db";

    public SQLSpectatorRepository() {
        openConnection();
        createTable();
        //initSpectatoriTable();
        loadData();
    }

    private void loadData() {
        entities.addAll(this.findAll());
    }


    private void initSpectatoriTable() {
        List<Spectator> spectatoriList = new ArrayList<>();

        spectatoriList.add(new Spectator(100,"Nume1","Prenume1","test1@gmail.com","parola1","+4070000000000"));
        spectatoriList.add(new Spectator(101,"Nume2","Prenume2","test2@gmail.com","parola2","+4070000000001"));
        spectatoriList.add(new Spectator(102,"Nume3","Prenume3","test3@gmail.com","parola3","+4070000000002"));
        spectatoriList.add(new Spectator(103,"Nume4","Prenume4","test4@gmail.com","parola4","+4070000000003"));
        spectatoriList.add(new Spectator(104,"Nume5","Prenume5","test5@gmail.com","parola5","+4070000000004"));

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO spectatori VALUES (?,?,?,?,?,?);")) {
            for (Spectator s : spectatoriList) {
                statement.setInt(1, s.getId());
                statement.setString(2, s.getNume());
                statement.setString(3, s.getPrenume());
                statement.setString(4, s.getEmail());
                statement.setString(5, s.getParola());
                statement.setString(6, s.getNumarTelefon());
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
            System.out.println("eroare la crearea conexiuni" + e);
        }
    }

    private void createTable() {
        String s = "Create Table if not exists spectatori( id_s int, nume varchar(30), prenume varchar(30), email varchar(100), parola varchar(30) ,numarTelefon varchar(12),  PRIMARY KEY (id_s) )";
        try {

            Statement statement = connection.createStatement();
            boolean execution_result = statement.execute(s);
            System.out.println("Execution result from createTable()" + execution_result);
        } catch (SQLException e) {
            System.out.println("eroare la crearea tabelei spectatori" + e);
        }
    }

    @Override
    public void add(Spectator spec) throws Exception {
        super.add(spec);
        String s = "INSERT INTO spectatori VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement add_statement = connection.prepareStatement(s);

            add_statement.setInt(1, spec.getId());
            add_statement.setString(2, spec.getNume());
            add_statement.setString(3, spec.getPrenume());
            add_statement.setString(4, spec.getEmail());
            add_statement.setString(5, spec.getParola());
            add_statement.setString(6, spec.getNumarTelefon());

            add_statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void update(Spectator oldSpectator, Spectator newSpectator) throws Exception {
        int index = entities.indexOf(oldSpectator);
        if (index == -1) {
            throw new Exception("Spectatorul cu ID-ul " + oldSpectator.getId() + " nu există.");
        }

        String sql = "UPDATE spectatori SET nume = ?, prenume = ?, email = ?, parola = ?, numarTelefon = ? WHERE id_s = ?;";
        try (PreparedStatement updateStatement = connection.prepareStatement(sql)) {
            updateStatement.setString(1, newSpectator.getNume());
            updateStatement.setString(2, newSpectator.getPrenume());
            updateStatement.setString(3, newSpectator.getEmail());
            updateStatement.setString(4, newSpectator.getParola());
            updateStatement.setString(5, newSpectator.getNumarTelefon());
            updateStatement.setInt(6, oldSpectator.getId());

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("Eroare la actualizare: nu a fost găsit niciun spectator cu ID-ul " + oldSpectator.getId());
            }
        } catch (SQLException e) {
            throw new Exception("Eroare SQL la actualizare: " + e.getMessage());
        }

        entities.set(index, newSpectator);
    }


    @Override
    public List<Spectator> findAll() {
        List<Spectator> resultList = new ArrayList<>();
        String s = "SELECT * FROM spectatori";
        try (PreparedStatement getAllSstatement = connection.prepareStatement(s)) {
            ResultSet result = getAllSstatement.executeQuery();
            while (result.next()) {
                Spectator spec = new Spectator(result.getInt("id_s"), result.getString("nume"),
                        result.getString("prenume"), result.getString("email"), result.getString("parola"), result.getString("numarTelefon"));
                resultList.add(spec);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}