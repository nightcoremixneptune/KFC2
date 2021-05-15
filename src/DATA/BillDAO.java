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
           
            String sql = "SELECT * FROM hoadon";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_hoadon= rs.getString("id_hoadon");
                String id_khach = rs.getString("id_khach");
                String id_nhanvien = rs.getString("id_nhanvien");
                String id_khuyenmai = rs.getString("id_khuyenmai");              
                String ngaylap = rs.getString("ngaylap");
                int tongtien = rs.getInt("tongtien");
                Bill b = new Bill(id_hoadon, id_khach, id_nhanvien, id_khuyenmai, ngaylap, tongtien);
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
            String sql = "UPDATE hoadon SET ";
            sql += "id_khach='"+b.getId_khach()+"', ";
            sql += "id_nhanvien='"+b.getId_nhanvien()+"', ";
            sql += "id_khuyenmai='"+b.getId_khuyenmai()+"', ";
            sql += "ngaylap='"+b.getNgaylap()+"', ";
            sql += "tongtien='"+b.getTongtien()+"', ";
            sql += " WHERE id_hoadon='"+b.getId_hoadon()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Bill b) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hoadon VALUES (";
                sql += "'"+b.getId_hoadon()+"',";
                sql += "'"+b.getId_khach()+"',";
                sql += "'"+b.getId_nhanvien()+"',";
                sql += "'"+b.getId_khuyenmai()+"',";
                sql += "'"+b.getNgaylap()+"',";
                sql += "'"+b.getTongtien()+")";
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
