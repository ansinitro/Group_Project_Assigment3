package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "15691804";
    private static final String DB_URL = "jdbc:postgresql://localhost:7777/apartment";
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            return con;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
