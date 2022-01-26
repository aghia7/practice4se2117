package data;

import java.sql.Connection;

public interface DB {
    Connection getConnection();
    void close();
}
