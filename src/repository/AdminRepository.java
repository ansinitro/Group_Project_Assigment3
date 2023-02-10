package repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import data.interfaces.IDB;
import repository.interfaces.IAdminRepository;

public class AdminRepository implements IAdminRepository {
    private final IDB db;

    public AdminRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createPerson(Person person) {
        Connection con = null;
        try{
            con = db.getConnection();
            if (person.getPhone_number().equals("no number")) {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO members(name, surname, iin) VALUES (?,?,?)");
                stmt.setString(1, person.getName());
                stmt.setString(2, person.getSurname());
                stmt.setString(3, person.getIin());

                stmt.execute();

                return true;
            }
            else {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO members(name, surname, phone_number, iin) VALUES (?,?,?,?)");
                stmt.setString(1, person.getName());
                stmt.setString(2, person.getSurname());
                stmt.setString(3, person.getPhone_number());
                stmt.setString(4, person.getIin());

                stmt.execute();

                return true;
            }
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
    public Person getMemberInfo(String iin) {
        Connection con = null;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT id, name, surname, phone_number, iin FROM users WHERE iin = ?");
            stmt.setString(1, iin);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person person;
                if (rs.getString("phone_number").equals("")) {
                    person = new Person(rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("iin"));

                }
                else {
                    person = new Person(rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("phone_number"),
                            rs.getString("iin"));

                } return person;
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
    public List<Person> getAllList() {
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT iin, name, surname, phone_number FROM members");
            List<Person> members = new LinkedList<>();
            while (rs.next()) {
                Person person = new Person(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("iin"));

                members.add(person);
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
}

