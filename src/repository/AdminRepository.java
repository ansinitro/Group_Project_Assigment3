package repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import data.interfaces.IDB;
import enteties.Member;
import repository.interfaces.IAdminRepository;

import javax.swing.plaf.nimbus.State;

public class AdminRepository implements IAdminRepository {
    private final IDB db;

    public AdminRepository(IDB db) {
        this.db = db;
    }


    @Override
    public Member getMemberInfo(String iin) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, surname, phone_number, pk_iin, apartment, room FROM member WHERE iin = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, iin);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Member member = new Member(rs.getString("name"),
                            rs.getString("surname"),
                        rs.getString("phone_number"),
                            rs.getString("iin"),
                        rs.getInt("apartment"),
                        rs.getInt("room"));

                    return member;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT name, surname, phone_number, pk_iin, apartment, room " +
                    "FROM member " +
                    "ORDER BY apartment, room, name");
            List<Member> members = new LinkedList<>();
            while (rs.next()) {
                Member member = new Member(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("pk_iin"),
                        rs.getInt("apartment"),
                        rs.getInt("room"));

                members.add(member);
            }

            return members;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean settlement(Member member) {
        Connection con = null;
        try{
            // check is the room is free or not
            con = db.getConnection();
            String sqlcheck = "select occupancy from room where fk_apartment = ? and room_number = ?";
            PreparedStatement stmt = con.prepareStatement(sqlcheck);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());
            ResultSet rs = stmt.executeQuery();

            if(!rs.next())
                return false;
            while(rs.next()) {
                if (rs.getBoolean("occupancy"))
                    return false;

            }
            // add the member into the member table in db
            String sql = "INSERT INTO member(name, surname, phone_number, pk_iin, apartment, room) VALUES (" + member.getName()
                    + "," + member.getSurname() + "," + member.ge;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getSurname());
            stmt.setString(3, member.getPhone_number());
            stmt.setString(4, member.getIin());
            stmt.setInt(5, member.getApartment());
            stmt.setInt(6, member.getRoom());
            stmt.executeUpdate();
            // check how many member in the room
            sql = "SELECT COUNT(*) FROM member WHERE apartment = ? AND room = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next())
                 count = rs.getInt("count");
            // check capacity of the room
            sql = "SELECT capacity FROM room WHERE fk_apartment = ? AND room_number = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());
            rs = stmt.executeQuery();
            int capacity = 0;
            while(rs.next())
                capacity = rs.getInt("capacity");
            // compare member number and capacity of the room
            if(count == capacity){
                // change the occupancy
                sql = "UPDATE room SET occupancy = true WHERE fk_apartment = ? AND room_number = ?;";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, member.getApartment());
                stmt.setInt(2, member.getRoom());
                stmt.executeUpdate();
                // check the occupancy of rooms in the apartment
                sql = "select count(distinct occupancy) from room where fk_apartment = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, member.getApartment());
                rs = stmt.executeQuery();
                while(rs.next()) {
                    count = rs.getInt("count");
                }
                // if all rooms occupancy equal to true apartment occupancy equal to true
                if (count == 1){
                    sql = "UPDATE apartment SET occupancy = true WHERE apartment_number = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, member.getApartment());
                    stmt.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean eviction_by_iin(String iin) {
        Connection con = null;
        try{
            Connection con = db.getConnection();
            String sql = "DELETE FROM member WHERE iin = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,iin);

            stmt.execute();





            // check the occupancy of rooms in the apartment
            sql = "update room set occupancy = false where ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, member.getApartment());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                count = rs.getInt("count");
            }
            // if all rooms occupancy equal to true apartment occupancy equal to true
            if (count == 1){
                sql = "UPDATE apartment SET occupancy = true WHERE apartment_number = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, member.getApartment());
                stmt.executeUpdate();





                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

