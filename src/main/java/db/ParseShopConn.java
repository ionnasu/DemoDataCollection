package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ParseShopConn {
    private static String userName;
    private static String password;
    private static String dbURL;
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            userName = "root";
            password = "";
//            password = "D3548572d_2";
//            dbURL = "jdbc:mysql://localhost:3306/info999";
            dbURL = "jdbc:mysql://localhost:3306/app_license?useUnicode=yes&characterEncoding=UTF-8";
//            dbURL = "jdbc:mysql://207.154.195.101:3306/app_license";
//            dbURL = "jdbc:mysql://localhost:3306/shop_and_parser?useUnicode=yes&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        }
        return connection;
    }
}

