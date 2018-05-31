package Query;

import db.avitoDB;
import Avito.set_get;
import Avito.ResultModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Avito.RegionsModel;

public class Avito extends avitoDB {
    private static Connection connection;
    private static Statement st;

    public static void GetRegions() throws SQLException {
        st = conn();
        String sql = "SELECT * FROM `regions` LIMIT 1";
        ResultSet resultSet = st.executeQuery(sql);
        if(resultSet.next()) {
           set_get.setCount(resultSet.getInt(1));
        }
        while (resultSet.next()){
            set_get.setName(resultSet.getString("name"));
            set_get.setId(resultSet.getInt("id"));
        }
        StopConn();
    }

    public static void InsertResults() throws SQLException {
        st = conn();
        String sql = "INSERT INTO `results`(`tag_id`, `region_id`, `name_contact`,`address`, `price`, `phone`, `tip_notice`, `name_notice`, `text`, `url`, `publish_date`, `shop_name`, `aditional_info`)"+
                " VALUES ( '"+ ResultModel.getTag_id() +"','"+ ResultModel.getRegion_id() +"', '"+ ResultModel.getName_contact() + "', '"+ ResultModel.getAddress() + "', '"+ ResultModel.getPrice() + "','"+ ResultModel.getPhone() + "', '"+ ResultModel.getTip_notice() + "'," +
                " '"+ ResultModel.getName_notice() + "','"+ ResultModel.getText() + "','"+ ResultModel.getUrl() + "','"+ ResultModel.getPublish_date() + "','"+ ResultModel.getShop_name() + "','"+ ResultModel.getAditional_info() + "')";

        st.execute(sql);
        StopConn();
    }

    public static void InsertRegions(String name) throws SQLException {
        st = conn();
        String sql = "INSERT INTO `regions`(`name`) VALUES ('"+name+"')";
        st.execute(sql);
        StopConn();
    }


    public static List<String> GetAll() throws SQLException {
        List<String> ListNumber = new ArrayList<>();
        st = conn();
        ResultSet rs1 = st.executeQuery("SELECT * from `results` WHERE `parsed` is null");
        while (rs1.next()) {
            String name = rs1.getString("url");
            ListNumber.add(name);
        }
        StopConn();
        return ListNumber;
    }
    public static void Update(String table_name, int updated_id) throws SQLException {
        st = conn();
        String sql = "UPDATE `"+table_name+"` " +
                     "SET `parsed`=1, `name_contact` = '"+ResultModel.getName_contact()+"', `address` = '"+ResultModel.getAddress()+"', `phone` = '"+ResultModel.getPhone()+"', `shop_name` = '"+ResultModel.getShop_name()+"', `tip_notice` = '"+ResultModel.getTip_notice()+"'"+
                     " WHERE id ="+updated_id;
        st.executeUpdate(sql);
        StopConn();
    }
        public static int GetOne(String table_name, String condition) throws SQLException {
        int id = 0;
        st = conn();
        ResultSet rs1 = st.executeQuery("SELECT *from " + table_name + "  " +condition + "AND `parsed` is NULL");
        while (rs1.next()) {
            id = rs1.getInt("id");
        }
        StopConn();
        return id;
    }
    public static ArrayList<RegionsModel> getAllregions() throws SQLException {
        st = conn();
        ArrayList<RegionsModel> regions = new ArrayList<>();
        ResultSet rs1 = st.executeQuery("SELECT * from `regions` WHERE `id` > 10 ");
        while (rs1.next()) {
            RegionsModel region = new RegionsModel(rs1.getInt("id"), rs1.getString("name"));
            regions.add(region);
        }
        StopConn();
        return regions;
    }

    //==================== PRIVATE FUNCTIONS =========================
    private static Statement conn() throws SQLException {
        connection = avitoDB.Conn();
        st = connection.createStatement();
        return st;
    }
    private static void StopConn() throws SQLException {
        st.close();
        connection.close();
    }
}
