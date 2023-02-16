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
            con = db.getConnection();
            String sqlcheck = "select occupancy from room where fk_apartment = ? and room_number = ?";
            PreparedStatement stmt = con.prepareStatement(sqlcheck);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());
            ResultSet rs = stmt.executeQuery();
            if(rs.getBoolean("occupancy"))
                return false;

            String sql = "INSERT INTO member(name, surname, phone_number, iin) VALUES (?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getSurname());
            stmt.setString(3, member.getPhone_number());
            stmt.setString(4, member.getIin());
            stmt.setInt(5, member.getApartment());
            stmt.setInt(6, member.getRoom());
            stmt.execute();

            sql = "SELECT COUNT(*) FROM member WHERE apartment = ? AND room_number = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());

            int count = stmt.executeQuery().getInt("count");

            sql = "SELECT capacity FROM room WHERE fk_apartment = ? AND room_number = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, member.getApartment());
            stmt.setInt(2, member.getRoom());

            int capacity = stmt.executeQuery().getInt("capacity");

            if(count == capacity){
                sql = "UPDATE room SET occupancy = true WHERE fk_apartment = ? AND room_number = ?;";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, member.getApartment());
                stmt.setInt(2, member.getRoom());
                stmt.executeQuery();

                sql = "select count(distinct occupancy) from room where fk_apartment = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, member.getApartment());
                if (stmt.executeQuery().getInt("count") == capacity){
                    sql = "UPDATE apartment SET occupancy = true WHERE apartment_number = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, member.getApartment());
                    stmt.execute();
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
            con = db.getConnection();
            String sql = "DELETE FROM member WHERE iin = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,iin);

            stmt.execute();

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
}

