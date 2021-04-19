/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Combo;
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
public class ComboDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public ComboDAO() {
    }
    public ArrayList<Combo> list()
    {
        ArrayList<Combo> combol = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM combo ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_combo= rs.getString("id_combo");
                String name_combo = rs.getString("name_combo");
                String id_dish = rs.getString("id_dish");              
                String price_combo = rs.getString("price_combo");
                int status_combo = rs.getInt("status_combo");                
                Combo cb = new Combo(id_combo, name_combo, id_dish, price_combo, status_combo);
                combol.add(cb);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Combo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return combol;
    }

    public void set(Combo cb) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE combo SET ";
            sql += "name_combo='"+cb.getName_combo()+"', ";
            sql += "id_dish='"+cb.getId_dish()+"', ";
            sql += "price_combo='"+cb.getPrice_combo()+"', ";
            sql += "status_combo='"+cb.getStatus_combo()+"' ";
            sql += " WHERE id_combo='"+cb.getId_combo()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Combo cb) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO combo VALUES (";
                sql += "'"+cb.getId_combo()+"',";
                sql += "'"+cb.getName_combo()+"',";
                sql += "'"+cb.getId_dish()+"',";
                sql += "'"+cb.getPrice_combo()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String getId_combo)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE combo SET status_combo = 0 WHERE id_combo='"+getId_combo+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String getId_combo)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE combo SET status_combo = 1 WHERE id_combo='"+getId_combo+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
