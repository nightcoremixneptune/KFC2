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
           
            String sql = "SELECT * FROM chitiethoadon";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_hoadon= rs.getString("id_hoadon");
                String id_sp = rs.getString("id_sp");
                int soluongsp = rs.getInt("soluongsp");                                            
                int thanhtien= rs.getInt("thanhtien");
                                            
                Bill_Detail bd = new Bill_Detail(id_hoadon, id_sp, soluongsp, thanhtien);
                dshd.add(bd);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill_Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }

    public void add(Bill_Detail bd) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chitiethoadon VALUES (";
                sql += "'"+bd.getId_hoadon()+"',";
                sql += "'"+bd.getId_sp()+"',";
                sql += "'"+bd.getSoluongsp()+"',";
                sql += "'"+bd.getThanhtien()+")";
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
