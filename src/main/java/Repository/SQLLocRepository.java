package Repository;

import Domain.Loc;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLLocRepository extends RepoMemory<Loc> {
    Connection connection;
    //change URL here to relative path or your (absolute) path
    String db_url = "jdbc:sqlite:teatru.db";

    public SQLLocRepository() {
        openConnection();
        createTable();
        //initSpectatoriTable();
        loadData();
    }

    private void loadData() {
        entities.addAll(this.findAll());
    }



    private void initSpectatoriTable() {
        List<Loc> spectatoriList = new ArrayList<>();
        for(int i=1 ; i<=8 ; i++)
            for(int j=1 ; j<=18 ; j++) {
                spectatoriList.add(new Loc((i - 1) * 18 + j, i, "parter", (i - 1) * 18 + j, 20, false));
            }
        for(int i=1 ; i<=2 ; i++)
            for(int j=1 ; j<=14 ; j++) {
                spectatoriList.add(new Loc(144 + (i - 1) * 14 + j, i, "loja A", (i - 1) * 14 + j, 25, false));
                spectatoriList.add(new Loc(144 + 28 + 48 + (i - 1) * 14 + j, i, "loja C", (i - 1) * 14 + j, 25, false));
            }

        for(int i=1 ; i<=2 ; i++)
            for(int j=1 ; j<=24 ; j++) {
                spectatoriList.add(new Loc(144 + 28 + (i - 1) * 24 + j, i, "loja B", (i - 1) * 24 + j, 25, false));
            }


        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO locuri VALUES (?,?,?,?,?,?);")) {
            for (Loc l : spectatoriList) {
                statement.setInt(1, l.getId());
                statement.setInt(2, l.getRand());
                statement.setString(3, l.getLoja());
                statement.setInt(4, l.getNumar());
                statement.setDouble(5, l.getPret());
                statement.setBoolean(6, l.getStare());
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
        String s = "Create Table if not exists locuri( id_l int, rand int, loja varchar(10), numar int , pret double, stare boolean , PRIMARY KEY (id_l) )";
        try {

            Statement statement = connection.createStatement();
            boolean execution_result = statement.execute(s);
            System.out.println("Execution result from createTable()" + execution_result);
        } catch (SQLException e) {
            System.out.println("eroare la crearea tabelei locuri" + e);
        }
    }


    @Override
    public List<Loc> findAll() {
        List<Loc> resultList = new ArrayList<>();
        String s = "SELECT * FROM locuri";
        try (PreparedStatement getAllSstatement = connection.prepareStatement(s)) {
            ResultSet result = getAllSstatement.executeQuery();
            while (result.next()) {
                Loc l = new Loc(result.getInt("id_l"), result.getInt("rand"),
                        result.getString("loja"), result.getInt("numar"), result.getDouble("pret"), result.getBoolean("stare"));

                resultList.add(l);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}