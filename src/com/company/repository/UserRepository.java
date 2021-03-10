package com.company.repository;

import com.company.bean.Question;
import com.company.bean.User;
import com.company.database.IDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserInterface {

    private IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public User login(String login, String password) {

        Connection con = null;

        try {
            con = db.getConnection();

            String sql = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "'";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getBoolean( "isadmin")
                );

                return user;
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
    public User register(String name, String password) {
        Connection con = null;

        try {
            con = db.getConnection();

            String sql = "INSERT INTO users(login, password, isadmin) VALUES(?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, password);
            st.setBoolean(3, false);
            st.execute();

            return new User(name, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
