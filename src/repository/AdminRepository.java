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
        int count = 0, capacity = 0;
        try{
            // check is the room is free or not
            con = db.getConnection();
            String sql = "select occupancy from room where fk_apartment = " + member.getApartment() + " and room_number = "+ member.getRoom();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // check is there room and is this room is full
            if(!rs.next())
                return false;
            while(rs.next()) {
                if (rs.getBoolean("occupancy"))
                    return false;
            }

            // add the member into the member table in db
            sql = "INSERT INTO member(name, surname, phone_number, pk_iin, apartment, room) VALUES ('" + member.getName()
                    + "','" + member.getSurname() + "','" + member.getPhone_number() + "','" + member.getIin() + "','" + member.getApartment() + "','" + member.getRoom() + "')";
            stmt.executeUpdate(sql);

            // check how many member in the room
            sql = "SELECT COUNT(*) FROM member WHERE apartment = " + member.getApartment() + " AND room = " + member.getRoom();
            rs = stmt.executeQuery(sql);
            while(rs.next())
                 count = rs.getInt("count");
            // check capacity of the room
            sql = "SELECT capacity FROM room WHERE fk_apartment = " + member.getApartment() + " AND room_number = " + member.getRoom();
            rs = stmt.executeQuery(sql);
            while(rs.next())
                capacity = rs.getInt("capacity");

            // compare member number and capacity of the room
            if(count == capacity){
                // change the occupancy
                sql = "UPDATE room SET occupancy = true WHERE fk_apartment = " + member.getApartment() + " AND room_number = " + member.getRoom();
                stmt.executeUpdate(sql);

                // check the occupancy of rooms in the apartment
                sql = "select count(distinct occupancy) from room where fk_apartment = " + member.getApartment();
                rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    count = rs.getInt("count");
                }
                // if all rooms occupancy equal to true apartment occupancy equal to true
                if (count == 1){
                    sql = "UPDATE apartment SET occupancy = true WHERE apartment_number = " + member.getApartment();
                    stmt.executeUpdate(sql);
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
        int apartment = 0, room = 0;
        try {
            con = db.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT apartment, room FROM member WHERE pk_iin = '" + iin + "'";
            ResultSet rs = stmt.executeQuery(sql);


            // assign apartment and room variables
            while(rs.next()){
                apartment = rs.getInt("apartment");
                room = rs.getInt("room");
            }

            // delete member
            sql = "delete from member where pk_iin = '" + iin + "'";
            stmt.executeUpdate(sql);

            // update room occupancy to false
            sql = "update room set occupancy = false where fk_apartment = " + apartment + " and room_number = " + room;
            stmt.executeUpdate(sql);

            // if all rooms occupancy equal to true apartment occupancy equal to true
            sql = "update apartment set occupancy = false where apartment_number = " + apartment;
            stmt.executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                con.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }
}

