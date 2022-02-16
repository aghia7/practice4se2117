package data.postgres;

import data.DB;

import java.sql.*;

public class Postgres implements DB { // Singleton
    private Connection conn;
    private static final Postgres instance = new Postgres();

    private Postgres() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/simpleappdb";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(connectionUrl, "postgres", "0000");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Postgres getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
