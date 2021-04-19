/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Bill_Detail;
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
public class Bill_DetailDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public Bill_DetailDAO() {
    }
    public ArrayList<Bill_Detail> list()
    {
        ArrayList<Bill_Detail> dshd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM bill_details";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_bill= rs.getString("id_bill");
                int quantity_combo= rs.getInt("quantity_combo");
                String date = rs.getString("date");               
                String id_member = rs.getString("id_member"); 
                int price_combo = rs.getInt("price_combo");                              
                int sale= rs.getInt("sale");
                                            
                Bill_Detail bd = new Bill_Detail( id_bill,  quantity_combo,  date,  id_member,  price_combo,  sale);
                dshd.add(bd);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill_Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }

    public void set(Bill_Detail bd) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE bill_details SET ";
            sql += "quantity_combo='"+bd.getQuantity_combo()+"', ";
            sql += "date='"+bd.getDate()+"', ";
            sql += "id_member='"+bd.getId_member()+"', ";
            sql += "price_combo='"+bd.getPrice_combo()+"', ";
            sql += "sale='"+bd.getSale()+"', ";
            sql += " WHERE id_bill='"+bd.getId_bill()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Bill_Detail bd) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO bill_details VALUES (";
                sql += "'"+bd.getId_bill()+"',";
                sql += "'"+bd.getQuantity_combo()+"',";
                sql += "'"+bd.getDate()+"',";
                sql += "'"+bd.getId_member()+"',";
                sql += "'"+bd.getPrice_combo()+"',";
                sql += "'"+bd.getSale()+")";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
/*    public void delete(String MaB)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE storage SET status_storage = 0 WHERE id_bill='"+MaB+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String MaB)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE storage SET status_storage = 1 WHERE id_bill='"+MaB+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
*/

}
