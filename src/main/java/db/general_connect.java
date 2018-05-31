package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class general_connect {

    private static String userName;
    private static String password;
    private static String dbURL;
    private static Connection connection;

    public static Connection getConnection(String db_name) throws SQLException {
        try {
            userName = "root";
            password = "";
            dbURL = "jdbc:mysql://localhost:3306/"+db_name+"?useUnicode=yes&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        }
        return connection;
    }
}
