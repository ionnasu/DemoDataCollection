//package Query;
//
//import db.connect;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Querys {
//    private static Connection connection;
//    private static Statement st;
//
//    public static List<String > GetMakeUpMenu() throws SQLException {
//        String name = null;
//        int id ;
//        List<String> ListNumber = new ArrayList<String>();
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        ResultSet rs1 = st.executeQuery("SELECT *from makeupmenu WHERE `is_parsed` = 0");
//        while (rs1.next()) {
//            name = rs1.getString("url");
//            ListNumber.add(name);
//        }
//        st.close();
//        connection.close();
//        return ListNumber;
//    }
//    public static void InsertIntoMakeUp(String name , String url ,String price, int p_id, String id_elem, String category, String brand, String old_price1) throws SQLException {
//        st = Querys.conn();
//        String sql = "INSERT INTO `make_up_price`(`name`, `url`, `price`, `p_id`, `id_element`, `category`, `brand`, `old_price`)"+
//                " VALUES ( '"+ name +"', '"+ url + "', '"+ price + "','"+p_id+"','"+id_elem+"','"+category+"','"+brand+"','"+old_price1+"')";
//        st.execute(sql);
//        Querys.StopConn();
//    }
//    public static void InsertIntoParfums(String name , String url ,String price, int p_id, String id_elem, String category, String brand, String old_price1) throws SQLException {
//        st = Querys.conn();
//        String sql = "INSERT INTO `price_parfums`(`name`, `url`, `price`, `p_id`, `id_element`, `category`, `brand`, `old_price`)"+
//                " VALUES ( '"+ name +"', '"+ url + "', '"+ price + "','"+p_id+"','"+id_elem+"','"+category+"','"+brand+"','"+old_price1+"')";
//        st.execute(sql);
//        Querys.StopConn();
//    }
//
//    public static void UpdateMakeUp(String url) throws SQLException {
//        st = Querys.conn();
//
//        String sql = "UPDATE `makeupmenu` " +
//                "SET `is_parsed`=1"+
//                " WHERE  `url` = '"+url+"'";
//        st.executeUpdate(sql);
//        st.execute(sql);
//
//        Querys.StopConn();
//
//    }
//
//    public static int GetMakeMenuByURl(String url) throws SQLException {
//        st = Querys.conn();
//        int id = 0 ;
//        ResultSet rs1 = st.executeQuery("SELECT *from makeupmenu WHERE `url` = '"+url+"'");
//        while (rs1.next()) {
//            id = rs1.getInt("id");
//        }
//        Querys.StopConn();
//        return id;
//    }
//
//
//
//
//
//
//    //*********************************************************************************************\\
//    public static List<String> GetAll(String table_name, String condition) throws SQLException {
//        String name = null;
//        int id ;
//        List<String> ListNumber = new ArrayList<String>();
//
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        ResultSet rs1 = st.executeQuery("SELECT *from " + table_name + "  " +condition);
//        while (rs1.next()) {
//            name = rs1.getString("url");
//            ListNumber.add(name);
//        }
//        st.close();
//        connection.close();
//        return ListNumber;
//    }
//    public static void InsertInto(String table_name , int p_id, String name , String url, int is_parsed) throws SQLException {
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        String sql = "INSERT INTO `"+table_name+"` (`p_id`, `name`, `url`, `is_parsed`)"+
//                " VALUES ( "+ p_id +", '"+ name +"', '"+ url + "', "+ is_parsed+")";
//        st.execute(sql);
//        st.close();
//        connection.close();
//    }
//    public static void Update(String table_name, int is_parsed, int updated_id) throws SQLException {
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        String sql = "UPDATE `"+table_name+"` " +
//                     "SET `is_parsed`="+is_parsed+""+
//                     " WHERE id ="+updated_id;
//        st.executeUpdate(sql);
//        st.close();
//        connection.close();
//    }
//    public static int GetOne(String table_name, String condition) throws SQLException {
//        int id = 0;
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        ResultSet rs1 = st.executeQuery("SELECT *from " + table_name + "  " +condition);
//        while (rs1.next()) {
//            id = rs1.getInt("id");
//        }
//        st.close();
//        connection.close();
//        return id;
//    }
//    public static Boolean Truncate(String table_name) throws SQLException {
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        String sql = "TRUNCATE table "+table_name;
//        st.execute(sql);
//        st.close();
//        connection.close();
//        return true;
//    }
//    public static void StartSession(String name_table) throws SQLException {
//        st = Querys.conn();
//        String sql = "INSERT INTO `session_fronts`(`table_session`) VALUES ('"+name_table+"')";
//        st.execute(sql);
//        Querys.StopConn();
//
//    }
//    public static int GetSession(String table_name) throws SQLException {
//        int id = 0;
//        st = Querys.conn();
//        String sql = "SELECT * FROM `session_fronts` where `is_started` = 1 and `is_closed` = 0 AND `table_session` = '"+table_name+"'";
//        ResultSet rs1 = st.executeQuery(sql);
//        while (rs1.next()) {
//            id = rs1.getInt("id");
//        }
//        Querys.StopConn();
//        return id;
//    }
//    public static void StopSession(int id) throws SQLException {
//        st = Querys.conn();
//        String sql = "UPDATE `session_fronts` " +
//                "SET `is_started` = 0," +
//                "    `is_closed` = 1 " +
//                "WHERE id =" + id;
//        st.execute(sql);
//        Querys.StopConn();
//    }
//
//    public static int CheckIfUserAddedToSession(int id_session) throws SQLException {
//        int id = 0;
//        st = Querys.conn();
//        String sql = "SELECT * FROM `users_front` where `session_id`= '"+id_session+"'";
//        ResultSet rs1 = st.executeQuery(sql);
//        while (rs1.next()) {
//            id = rs1.getInt("id");
//        }
//        Querys.StopConn();
//        return id;
//    }
//    public static int PostCaptcha(int p_id, int user_id, String img, String result) throws SQLException {
//        int id = 0;
//        st = Querys.conn();
//        String sql = "INSERT INTO `captcha_for_front`( `p_id`, `user_id`, `img`, `rezult`) VALUES ("+p_id+","+user_id+",'"+img+"','"+result+"')";
//        st.execute(sql);
//        ResultSet rs = st.getGeneratedKeys();
//        if (rs.next()){
//            id=rs.getInt(1);
//        }
//        Querys.StopConn();
//        return id;
//    }
//    public static String ReturnCaptchaVal(int id_captcha) throws SQLException {
//        String val = null;
//        st = Querys.conn();
//        String sql = "SELECT * FROM `captcha_for_front` where `id`= '"+id_captcha+"'";
//        ResultSet rs1 = st.executeQuery(sql);
//        while (rs1.next()) {
//            val = rs1.getString("rezult");
//        }
//        Querys.StopConn();
//        return val;
//    }
//
//    private static Statement conn() throws SQLException {
//        connection = connect.getConnection();
//        st = connection.createStatement();
//        return st;
//    }
//    private static void StopConn() throws SQLException {
//        st.close();
//        connection.close();
//    }
//}
