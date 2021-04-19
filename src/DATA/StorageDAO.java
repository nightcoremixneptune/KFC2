/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Storage;
import ConnectMysql.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class StorageDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public StorageDAO() {
    }
    public ArrayList<Storage> list()
    {
        ArrayList<Storage> dstp = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM storage";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_food= rs.getString("id_food");
                String brand_food = rs.getString("brand_food");
                String quantity_food = rs.getString("quantity_food");              
                String expiry_date = rs.getString("expiry_date");
                int status_food = rs.getInt("status_food");
                Storage tp = new Storage(id_food, brand_food, quantity_food, expiry_date, status_food);
                dstp.add(tp);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dstp;
    }

    public void set(Storage tp) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE storage SET ";
            sql += "brand_food='"+tp.getBrand_food()+"', ";
            sql += "quantity_food='"+tp.getQuantity_food()+"', ";
            sql += "expiry_date='"+tp.getExpiry_date()+"', ";
            sql += "status_food='"+tp.getStatus_food()+"', ";
            sql += " WHERE id_food='"+tp.getId_food()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Storage tp) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO storage VALUES (";
                sql += "'"+tp.getId_food()+"',";
                sql += "'"+tp.getBrand_food()+"',";
                sql += "'"+tp.getQuantity_food()+"',";
                sql += "'"+tp.getExpiry_date()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaTP)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE storage SET status_storage = 0 WHERE id_storage='"+MaTP+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String MaTP)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE storage SET status_storage = 1 WHERE id_storage='"+MaTP+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
