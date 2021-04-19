 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Sale;
import ConnectMysql.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thienan
 */
public class SaleDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public SaleDAO() {
    }

    public ArrayList<Sale> list() {
        ArrayList<Sale> sale = new ArrayList<>();
        try {

            String sql = "SELECT * FROM sale ";
            ResultSet rs = mySQL.executeQuery(sql);
            while (rs.next()) {
                String saleId = rs.getString("saleId");
                String saleEvent = rs.getString("saleEvent");
                int salePercent = rs.getInt("salePercent");
                String dishId = rs.getString("dishId");
                int status = rs.getInt("status");
                Sale s = new Sale(saleId, saleEvent, dishId, salePercent, status);
                sale.add(s);
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(Sale.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sale;
    }

    public void set(Sale s) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE sale SET ";
        sql += "saleEvent='" + s.getSaleEvent() + "', ";
        sql += "dishId='" + s.getDishId() + "', ";
        sql += "salePercent='" + s.getSalePercent() + "', ";
        sql += "status='" + s.getStatus() + "', ";
        sql += " WHERE saleId='" + s.getSaleId() + "'";
        System.out.println(sql);

        mySQL.executeUpdate(sql);
    }

    public void add(Sale s) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO crew VALUES (";
        sql += "'" + s.getSaleId() + "',";
        sql += "'" + s.getSaleEvent() + "',";
        sql += "'" + s.getDishId() + "',";
        sql += "'" + s.getSalePercent() + "',";
        sql += "'" + s.getStatus() + "',";
        sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String getId_crew) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE crew SET status_crew = 0 WHERE id_crew='" + getId_crew + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public void refesh(String getSaleId) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE sale SET status = 1 WHERE saleId='" + getSaleId + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}