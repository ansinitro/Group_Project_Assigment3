import Rooms.LivingRoom;
import Rooms.Room;
import org.w3c.dom.ls.LSInput;

import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "15691804";
    private static final String DB_URL = "jdbc:postgresql://localhost:7777/apartment";

    public static void main(String[] args) {
        ResultSet rs = null;
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO members (id, name, surname, phone_number, iin) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, 3);
            ps.setString(2, "Angsar");
            ps.setString(3, "fadgadgda");
            ps.setString(4, "741763");
            ps.setString(5, "41364153");
            ps.execute();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from members");

            while (rs.next())
                System.out.println(rs.getInt("id") +
                        " " + rs.getString("name") + " " + rs.getString("surname"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                System.out.println('');
            }
        }
    }
}