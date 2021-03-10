package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SurveyDatabase implements IDB {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "X187pp98");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
