package Query;

import TezaParser.setter;
import db.ParseShopConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TezaDB {
     private static Connection connection;
     private static Statement st;

     public static Integer CheckTasks() throws SQLException {
        st = conn();
        int count = 0;
        String sql = "SELECT *FROM `task_parser` where is_executed = 0 and is_runing = 0 LIMIT 1";
         ResultSet resultSet = st.executeQuery(sql);
         if(resultSet.next()) {
             count = resultSet.getInt(1);
         }
         StopConn();
         return count;
     }
     public static Integer CheckCompare() throws SQLException {
         st = conn();
         int nr = 0;
         String sql = "SELECT *FROM `task_compare` where is_executed = 0 and is_runing = 0 LIMIT 1";
         ResultSet resultSet = st.executeQuery(sql);
         if(resultSet.next()) {
             nr = resultSet.getInt(1);
         }
         StopConn();
         return nr;
     }
     public static void GetTaskCompare() throws SQLException {
         st = conn();
         String sql = "SELECT *FROM `task_compare` where is_executed = 0 and is_runing = 0 and was_started = 1  LIMIT 1";
         ResultSet rs1 = st.executeQuery(sql);
         while (rs1.next()) {
             setter.ReturnId(rs1.getInt("id"));
             setter.ReturnName(rs1.getString("platforme"));
             setter.setTag(rs1.getString("tag"));
             setter.setCompare(rs1.getInt("id"));
         }
         StopConn();
     }
     public static void CompletCompare(int id) throws SQLException {
         st = conn();
         String sql = "UPDATE `task_compare` " +
                 "SET `is_executed`= 1"+
                 " WHERE id ="+id;
         st.executeUpdate(sql);
         StopConn();
     }
     public static void RuningUpdateCompare(int id) throws SQLException {
         st = conn();
         String sql = "UPDATE `task_compare` " +
                 "SET `is_runing`= 1"+
                 " WHERE id ="+id;
         st.executeUpdate(sql);
         StopConn();
     }

     public static void GetOrders() throws SQLException {
        st = conn();
        String sql = "SELECT *FROM `task_parser` where is_executed = 0 and is_runing = 0 and was_started = 1  LIMIT 1";
        ResultSet rs1 = st.executeQuery(sql);
         while (rs1.next()) {
             setter.ReturnId(rs1.getInt("id"));
             setter.ReturnName(rs1.getString("platforme"));
         }
         StopConn();
     }

     public static boolean StartRuning(int id) throws SQLException {
         st = conn();
         String sql = "UPDATE `task_parser` " +
                 "SET `is_runing`= 1"+
                 " WHERE id ="+id;
         st.executeUpdate(sql);
         StopConn();
         return true;
     }
//==================== ALIEXPRESS Query ==========================
//    public static void InsertIntoAliexpress(String name , String url, String img, String short_descr,  String price ,String old_price, String features, String shipping) throws SQLException {
//        st = conn();
//        String sql = "INSERT INTO `aliexpress`(`name`, `img`, `url`, `short_descr`, `price`, `old_price`, `table_features`, `shipping`)"+
//                " VALUES ( '"+ name +"', '"+ img + "','"+ url + "', '"+ short_descr + "','"+price+"','"+old_price+"','"+features+"', '"+shipping+"')";
//        st.execute(sql);
//        StopConn();
//    }
//
    public static void InsertIntoAliexpress(int compare_id , int task_id, String tag, String name , String url, String img, String short_descr, String price , String old_price, String features, String shipping) throws SQLException {
        st = conn();
        String sql = "INSERT INTO `aliexpress`(`compare_id`,`tag_name`,`name`, `img`, `url`, `short_descr`, `price`, `old_price`, `table_features`, `shipping`)"+
                " VALUES ( '"+ compare_id +"','"+ tag +"','"+ name +"', '"+ img + "','"+ url + "', '"+ short_descr + "','"+price+"','"+old_price+"','"+features+"', '"+shipping+"')";
        st.execute(sql);
        StopConn();
    }
//    public static String GetAliexpressTask(int id) throws SQLException {
//        st = conn();
//        String sql = "SELECT *FROM `task_parser` where `id` = "+id+" LIMIT 1";
//        ResultSet rs1 = st.executeQuery(sql);
//        while (rs1.next()) {
//            setter.ReturnUrl(rs1.getString("url"));
//        }
//        StopConn();
//        return setter.getUrl();
//    }
//========================== END =================================


//==================== Amazon QUERY ==============================

    public static void InsertIntoAmazon(int p_id, int compare_id , int task_id, String tag, String name, String url, String img, String short_descr,  String price ,String old_price, String features, String shipping) throws SQLException {
        st = conn();
        String sql = "INSERT INTO `amazon`(`p_id`,`task_id`,`compare_id`,`tag_name`, `name`, `img`, `url`, `short_descr`, `price`, `old_price`, `table_features`, `shipping`)"+
                " VALUES ( '"+ p_id +"','"+ task_id +"','"+ compare_id +"','"+ tag +"','"+ name +"', '"+ img + "','"+ url + "', '"+ short_descr + "','"+price+"','"+old_price+"','"+features+"', '"+shipping+"')";
        st.execute(sql);
        StopConn();
    }

//========================== END =================================


//========================== METEO =================================

    public static void InsertIntoMeteo( String type_forecast, String date, String temp_min, String temp_max,  String wind, String location) throws SQLException {
        st = conn();
        String sql = "INSERT INTO `meteo`(`type_forecast`, `date`, `temp_min`, `temp_max`, `wind`,`location`)"+
                " VALUES ( '"+ type_forecast +"','"+ date +"', '"+ temp_min + "','"+ temp_max + "', '"+ wind + "', '"+ location + "')";
        st.execute(sql);
        StopConn();
    }

//========================== END =================================

//======================== GENERAL ===============================
public static String GetUrlFromTable( int id) throws SQLException {
    st = conn();
    String sql = "SELECT *FROM `task_parser` where `id` = "+id+" LIMIT 1";
    ResultSet rs1 = st.executeQuery(sql);
    while (rs1.next()) {
        setter.ReturnUrl(rs1.getString("url"));
        setter.setP_id(rs1.getInt("goods_subject_id"));
    }
    StopConn();
    return setter.getUrl();
}
//========================== END =================================

    //==================== PRIVATE FUNCTIONS =========================
     private static Statement conn() throws SQLException {
        connection = ParseShopConn.getConnection();
        st = connection.createStatement();
        return st;
    }
    private static void StopConn() throws SQLException {
        st.close();
        connection.close();
    }
}
