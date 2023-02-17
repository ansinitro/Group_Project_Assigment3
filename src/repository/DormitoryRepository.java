package repository;

import data.interfaces.IDB;
import enteties.Apartment;
import enteties.Room;
import repository.interfaces.IDormitoryRepository;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DormitoryRepository implements IDormitoryRepository {
    private final IDB db;

    public DormitoryRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Apartment> getFreeApartments() {
        Connection con = null;
        try{
            con = db.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select apartment_number, number_of_rooms from apartment where occupancy = false" +
                    " ORDER BY apartment_number";
            ResultSet rs = stmt.executeQuery(sql);
            List<Apartment> list_of_free_apartments = new LinkedList<Apartment>();

            while(rs.next()) {
                    Apartment apartment = new Apartment(rs.getInt("apartment_number"),
                            rs.getInt("number_of_rooms"));
                    list_of_free_apartments.add(apartment);
            }

            return list_of_free_apartments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Room> getFreeRooms(int apartment) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT distinct(room_number), capacity FROM room WHERE occupancy = false and fk_apartment = ?" +
                    " ORDER BY room_number";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, apartment);
            ResultSet rs = stmt.executeQuery();
            List<Room> list_of_free_rooms = new LinkedList<Room>();

            while(rs.next()) {
                    Room room = new Room(rs.getInt("room_number"),
                            rs.getInt("capacity"));
                    list_of_free_rooms.add(room);
            }

            return list_of_free_rooms;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String getInformationAboutDormitory() {
        return "Dormitory BLOCK 6 of AITU:\nThere are 7 floors, and 6 apartment on each floor.\n" +
                "\nGraphic of work:\n   Open: 6:00 - 23:00\n   Close: 23-00 - 6:00";
    }
}
