package Rooms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;


public class LivingRoom extends Room {
    private int numberOfBed;

    public LivingRoom(double square, double height){
        super(square, height);
    }

    public LivingRoom(double square, double height, int numberOfBed){
        super(square, height);
        this.numberOfBed = numberOfBed;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void settlement(String name,String surname,String phone,String iin){
        String connectionUrl = "jdbc:postgresql://localhost:7777/apartment";
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "postgres", "15691804");
            PreparedStatement ps = con.prepareStatement("INSERT INTO members (id, name, surname, phone_number, iin) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, 0);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setString(4, phone);
            ps.setString(5, "41364153");
            ps.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

