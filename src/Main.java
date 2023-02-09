import Rooms.LivingRoom;
import Rooms.Room;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:7777/apartment";
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;

        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "postgres", "15691804");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from members");

            while(rs.next())
                System.out.println(rs.getInt("id") +
                        " " + rs.getString("name") + " " + rs.getString("surname"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        Room living = new LivingRoom(50, 3, 10);
        System.out.println(living.getSquare());
        living.switchOffLight();
        
    }
}