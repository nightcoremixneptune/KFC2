/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Dish;
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
public class DishDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public DishDAO() {
    }
    public ArrayList<Dish> list()
    {
        ArrayList<Dish> dsma = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM dish";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_dish= rs.getString("id_dish");
                String name_dish = rs.getString("name_dish");
                String id_food = rs.getString("id_food");              
                String food_consume = rs.getString("food_consume");
                int status_dish = rs.getInt("status_dish");
                Dish dh = new Dish(id_dish, name_dish, id_food, food_consume, status_dish);
                dsma.add(dh);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dish.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsma;
    }

    public void set(Dish dh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE dish SET ";
            sql += "name_dish='"+dh.getName_dish()+"', ";
            sql += "id_food='"+dh.getId_food()+"', ";
            sql += "food_consume='"+dh.getFood_consume()+"', ";
            sql += "status_dish='"+dh.getStatus_dish()+"', ";
            sql += " WHERE id_dish='"+dh.getId_dish()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Dish dh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO dish VALUES (";
                sql += "'"+dh.getId_dish()+"',";
                sql += "'"+dh.getName_dish()+"',";
                sql += "'"+dh.getId_food()+"',";
                sql += "'"+dh.getFood_consume()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE dish SET status_dish = 0 WHERE id_dish='"+MaMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String MaMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE dish SET status_dish = 1 WHERE id_dish='"+MaMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
