package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class avitoDB extends general_connect {

    public static Connection Conn() throws SQLException {
        return general_connect.getConnection("avito");
    }
}
