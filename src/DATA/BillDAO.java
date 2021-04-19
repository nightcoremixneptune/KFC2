/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Bill;
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
public class BillDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public BillDAO() {
    }
    public ArrayList<Bill> list()
    {
        ArrayList<Bill> dshd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM bill";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_bill= rs.getString("id_bill");
                String id_combo = rs.getString("id_combo");
                String id_crew = rs.getString("id_crew");              
                String id_sale = rs.getString("id_sale");
                int sum = rs.getInt("sum");
                Bill b = new Bill(id_bill, id_combo, id_crew, id_sale, sum);
                dshd.add(b);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }

    public void set(Bill b) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE bill SET ";
            sql += "id_combo='"+b.getId_combo()+"', ";
            sql += "id_crew='"+b.getId_crew()+"', ";
            sql += "id_sale='"+b.getId_sale()+"', ";
            sql += "sum='"+b.getSum()+"', ";
            sql += " WHERE id_bill='"+b.getId_bill()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Bill b) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO bill VALUES (";
                sql += "'"+b.getId_bill()+"',";
                sql += "'"+b.getId_combo()+"',";
                sql += "'"+b.getId_crew()+"',";
                sql += "'"+b.getId_sale()+"',";
                sql += "'"+b.getSum()+")";
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
