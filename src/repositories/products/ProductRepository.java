package repositories.products;

import data.DB;
import data.postgres.Postgres;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final DB db;

    public ProductRepository(DB db) {
        this.db = db;
    }

    @Override
    public Product get(int id) {
        Product product = null;
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = Product.builder(rs.getString("name"))
                        .withId(rs.getInt("id"))
                        .withCategory(rs.getString("category"))
                        .withPrice(rs.getDouble("price")).build();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                Product product = Product.builder(rs.getString("name"))
                        .withId(rs.getInt("id"))
                        .withCategory(rs.getString("category"))
                        .withPrice(rs.getDouble("price")).build();

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    @Override
    public boolean create(Product product) {
        try {
            Connection conn = db.getConnection();
            String sql = "INSERT INTO products(name, category, price) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE category = ?");
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = Product.builder(rs.getString("name"))
                        .withId(rs.getInt("id"))
                        .withCategory(rs.getString("category"))
                        .withPrice(rs.getDouble("price")).build();

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }
}
