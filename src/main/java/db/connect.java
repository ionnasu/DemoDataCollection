//package db;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class connect {
//    private static String userName;
//    private static String password;
//    private static String dbURL;
//    private static Connection connection;
//
//    public static Connection getConnection() throws SQLException {
//        try {
//            connect.userName = "root";
//            connect.password = "";
////            dbURL = "jdbc:mysql://localhost:3306/info999";
//            connect.dbURL = "jdbc:mysql://localhost:3306/ParserPeople?useUnicode=yes&characterEncoding=UTF-8";
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(connect.dbURL, connect.userName, connect.password);
//        } catch (ClassNotFoundException e) {
//            System.out.println("MySQL JDBC driver not found.");
//            e.printStackTrace();
//        }
//        return connection;
//    }
//}
