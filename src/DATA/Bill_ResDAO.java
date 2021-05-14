/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import ConnectMysql.MySQLConnect;
import ConnectMysql.MySQLConnect2;
import DTO.Bill;
import DTO.Bill_Res;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Bill_ResDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public Bill_ResDAO() {
    }
    public ArrayList<Bill_Res> list()
    {
        ArrayList<Bill_Res> dshd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM hoadon";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                int id_hoadon= rs.getInt("id_hoadon");
                int id_khach = rs.getInt("id_khach");
                int id_nhanvien = rs.getInt("id_nhanvien");
                int id_khuyenmai = rs.getInt("id_khuyenmai");              
                String ngaylap = rs.getString("ngaylap");
                int tongtien = rs.getInt("tongtien");
                Bill_Res b = new Bill_Res(id_hoadon, id_khach, id_nhanvien, id_khuyenmai, ngaylap, tongtien);
                dshd.add(b);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }

    public void set(Bill_Res b) {
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

    public void add(Bill_Res b) {
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
}
