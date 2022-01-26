package repositories.users;

import data.DB;
import data.postgres.Postgres;
import models.User;
import repositories.EntityRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements EntityRepository<User> {
    private final DB db;

    public UserRepository(DB db) {
        this.db = db;
    }

    @Override
    public User get(int id) {
        User user = null;
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id=" + id);

            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}
